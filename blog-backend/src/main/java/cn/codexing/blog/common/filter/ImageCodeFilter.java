package cn.codexing.blog.common.filter;

import cn.codexing.blog.common.enums.ResultEnum;
import cn.codexing.blog.common.exception.ImageCodeException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {
    /**
     * 哪些地址需要图片验证码进行验证
     */
    private Set<String> urls = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urls.add("/user/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        boolean action = false;
        String t = httpServletRequest.getRequestURI();
        for (String url : urls) {
            if (antPathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
                break;
            }
        }
        if (action) {
            try {
                /*图片验证码是否正确*/
                checkImageCode(httpServletRequest);
            } catch (ImageCodeException e) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", ResultEnum.ERROR);
                jsonObject.put("msg", e.getMessage());
                httpServletResponse.getWriter().write(jsonObject.toJSONString());
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * Description:验证图片验证码是否正确
     *
     * @param httpServletRequest
     * @author huangweicheng
     * @date 2019/10/22
     */
    private void checkImageCode(HttpServletRequest httpServletRequest) {
        /*从cookie取值*/
        Cookie[] cookies = httpServletRequest.getCookies();
        String uuid = "";
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if ("captcha".equals(cookieName)) {
                uuid = cookie.getValue();
            }
        }
        String redisImageCode = (String) redisTemplate.opsForValue().get(uuid);
        /*获取图片验证码与redis验证*/
        String imageCode = httpServletRequest.getParameter("imageCode");
        /*redis的验证码不能为空*/
        if (StringUtils.isEmpty(redisImageCode) || StringUtils.isEmpty(imageCode)) {
            throw new ImageCodeException("验证码不能为空");
        }
        /*校验验证码*/
        if (!imageCode.equalsIgnoreCase(redisImageCode)) {
            throw new ImageCodeException("验证码错误");
        }
        redisTemplate.delete(redisImageCode);
    }
}