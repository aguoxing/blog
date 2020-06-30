package cn.codexing.blog.common.base;

import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @Author: guoxing
 * @Date: 2020/3/19 8:25
 * @Email: 2933575030@qq.com
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException exception) {
        log.error("统一异常处理", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }
}
