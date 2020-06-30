package cn.codexing.blog.common.handler;

import cn.codexing.blog.common.enums.ResultEnum;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GuoXing
 */
@Service
public class LoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 日志记录
     */
    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("用户" + request.getParameter("username") + "登录失败");
        String content = e.getMessage();
        //TODO 后期改进密码错误方式，统一处理
        String temp = "Bad credentials";
        if (temp.equals(e.getMessage())) {
            content = "用户名或密码错误";
        }
        response.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", e.getMessage());
        jsonObject.put("code", ResultEnum.ERROR.getCode());
        jsonObject.put("msg", content);
        response.getWriter().write(jsonObject.toJSONString());
    }
}
