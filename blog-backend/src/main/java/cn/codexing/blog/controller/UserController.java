package cn.codexing.blog.controller;


import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.util.VerifyCodeUtils;
import cn.codexing.blog.entity.SysUser;
import cn.codexing.blog.oauth.GithubService;
import cn.codexing.blog.oauth.QQService;
import cn.codexing.blog.security.JwtUser;
import cn.codexing.blog.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Api("用户控制")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private GithubService githubService;
    @Autowired
    private QQService qqService;

    @ApiOperation("图片验证码")
    @GetMapping("/verifyCode.jpg")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*禁止缓存*/
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        /*获取验证码*/
        String code = VerifyCodeUtils.generateVerifyCode(4);
        /*验证码已key，value的形式缓存到redis 存放时间一分钟*/
        log.info("验证码============>" + code);
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid, code, 1, TimeUnit.MINUTES);
        Cookie cookie = new Cookie("captcha", uuid);
        /*key写入cookie，验证时获取*/
        response.addCookie(cookie);
        ServletOutputStream outputStream = response.getOutputStream();
        //ImageIO.write(bufferedImage,"jpg",outputStream);
        VerifyCodeUtils.outputImage(110, 40, outputStream, code);
        outputStream.flush();
        outputStream.close();
    }

    @ApiOperation("Github登录")
    @GetMapping("/login/github")
    public Result<Object> loginWithGithub() {
        String url = githubService.getUrl();
        return new Result<>(url);
    }

    @ApiOperation("获取GitHub登录用户信息")
    @GetMapping("/info/github")
    public Result<Object> getGithubUserInfo(@RequestParam("code") String code) {
        Object user = githubService.callback(code);
        return new Result<>(user);
    }

    @ApiOperation("QQ用户登录")
    @GetMapping("/login/qq")
    public Result<Object> loginWithQq(){
        String url = qqService.getUrl();
        return new Result<>(url);
    }

    @ApiOperation("获取QQ登录用户信息")
    @GetMapping("/info/qq")
    public Result<Object> getQqUserInfo(@RequestParam("code") String code) {
        Object user = githubService.callback(code);
        return new Result<>(user);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<Object> saveUser(@RequestBody SysUser user, HttpServletRequest request) {
        userService.register(user, request);
        return new Result<>("注册成功");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/myinfo/{id}")
    public Result<SysUser> getMyInfo(@PathVariable Integer id) {
        SysUser user = userService.getUserInfo(id);
        return new Result<>(user);
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/info")
    public Result<Object> userInfo() {
        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails.setPassword("[HIDE]");
        return new Result<>(userDetails);
    }

    @ApiOperation("删除用户")
    @PutMapping("/delete/{id}")
    public Result<Object> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new Result<>("删除成功");
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/update")
    public Result<Object> updateUser(@RequestBody SysUser user) {
        userService.updateUserInfo(user);
        return new Result<>("修改成功");
    }

    @ApiOperation("查询所有用户")
    @GetMapping("/list")
    public Result<List<SysUser>> listUser() {
        List<SysUser> users = userService.list();
        return new Result<>(users);
    }

}
