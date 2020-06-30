package cn.codexing.blog.common.util;

import cn.codexing.blog.entity.SysLog;
import lombok.Data;

/**
 * @Author: guoxing
 * @Date: 2020/3/18 12:14
 * @Email: 2933575030@qq.com
 */
@Data
public class ThreadLocalContextUtils {

    /**
     * 日志实体类
     */
    private SysLog logger = new SysLog();

    /**
     * 是否记录日志
     */
    private boolean isLog = false;

    /**
     * 线程本地内存中的变量
     */
    private static ThreadLocal<ThreadLocalContextUtils> threadLocal = new ThreadLocal<>();

    public static ThreadLocalContextUtils get() {
        if (threadLocal.get() == null) {
            ThreadLocalContextUtils threadLocalContext = new ThreadLocalContextUtils();
            threadLocal.set(threadLocalContext);
        }
        ThreadLocalContextUtils threadLocalContext = threadLocal.get();
        return threadLocalContext;
    }

    public void remove() {
        this.logger = null;
        this.isLog = false;
        threadLocal.remove();
    }
}
