package cn.codexing.blog.common.aspect;

import cn.codexing.blog.common.enums.StateEnum;
import cn.codexing.blog.common.util.IPUtils;
import cn.codexing.blog.common.util.StringUtils;
import cn.codexing.blog.common.util.ThreadLocalContextUtils;
import cn.codexing.blog.entity.SysLog;
import cn.codexing.blog.service.SysLogService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: guoxing
 * @Date: 2020/3/17 10:50
 * @Email: 2933575030@qq.com
 */
@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 定义切面
     * 两个..代表所有子目录，最后两个..代表所有参数
     */
    @Pointcut("execution(* cn.codexing.blog.controller..*(..))")
//    @Pointcut("execution(* cn.codexing.blog.controller.UserController.*(..))")
    public void pointCut() {

    }

    /**
     * 方法执行之前调用
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接受到请求 记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();

        // 记录请求内容
        printRequestLog(joinPoint, request, uri);
    }


    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时：{}", time);

        SysLog logger = ThreadLocalContextUtils.get().getLogger();
        logger.setUsedTime(time);
        return object;
    }

    /**
     * 后置通知
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "pointCut()")
    public void doAfterReturning(Object obj) {
        log.info("返回值：{}", JSON.toJSONString(obj));
        String result = JSON.toJSONString(obj);
        SysLog logger = ThreadLocalContextUtils.get().getLogger();
        logger.setResult(result);
        sysLogService.save(logger);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        SysLog logger = ThreadLocalContextUtils.get().getLogger();
        logger.setStatus(StateEnum.REQUEST_ERROR.getCode());
        String exception = StringUtils.getPackageException(e, "cn.codexing");
        System.out.println("==="+exception+"=====");
        logger.setMessage(exception);
        sysLogService.save(logger);
    }

    /**
     * 打印请求日志
     *
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        log.info("请求地址：{}", uri);
        log.info("请求方式：{}", request.getMethod());
        // 获取真实ip地址
        String ip = IPUtils.getIpAddr(request);
        log.info("请求IP：{}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("方法：{}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());
        String ua = request.getHeader("user-Agent");
        log.info("浏览器：{}", ua);

        // 获取日志实体类
        SysLog logger = ThreadLocalContextUtils.get().getLogger();
        logger.setUrl(uri);
        logger.setParams(params);
        logger.setStatus(StateEnum.REQUEST_SUCCESS.getCode());
        logger.setMethod(request.getMethod());
        logger.setIp(ip);
    }

}
