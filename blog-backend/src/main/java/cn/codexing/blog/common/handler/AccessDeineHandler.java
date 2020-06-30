package cn.codexing.blog.common.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理已认证用户 访问无权限资源
 * @author xing
 */
@Service
public class AccessDeineHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",e.getMessage());
        jsonObject.put("code", HttpStatus.FORBIDDEN);
        jsonObject.put("msg", "您无权限进行该操作！！！");
        httpServletResponse.getWriter().write(jsonObject.toJSONString());
    }
}
