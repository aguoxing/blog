package cn.codexing.blog.common.handler;

import cn.codexing.blog.common.enums.ResultEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResultEnum.SUCCESS.getCode());
        jsonObject.put("msg","退出成功");
        jsonObject.put("data","null");
        httpServletResponse.getWriter().write(jsonObject.toJSONString());
    }
}
