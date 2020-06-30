package cn.codexing.blog.common.handler;

import cn.codexing.blog.common.enums.ResultEnum;
import cn.codexing.blog.common.util.JwtTokenUtils;
import cn.codexing.blog.security.JwtUser;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 日志记录
     */
    private static final Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("用户为====>" + httpServletRequest.getParameter("username") + "登录成功");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        /*获取用户权限信息*/
        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        String token = jwtTokenUtils.generateToken(userDetails);
        /*存储redis并设置了过期时间*/
        redisTemplate.boundValueOps(userDetails.getUsername() + "codexing").set(token,10, TimeUnit.MINUTES);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResultEnum.SUCCESS.getCode());
        jsonObject.put("msg","登录成功");
        jsonObject.put("token",token);
        /*认证信息写入header*/
        httpServletResponse.setHeader("Authorization",token);
        httpServletResponse.getWriter().write(jsonObject.toJSONString());
    }
}
