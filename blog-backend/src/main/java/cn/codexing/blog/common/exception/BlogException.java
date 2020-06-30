package cn.codexing.blog.common.exception;

import cn.codexing.blog.common.enums.ResultEnum;

/**
 * @Author: guoxing
 * @Date: 2020/3/18 9:56
 * @Email: 2933575030@qq.com
 */
public class BlogException extends RuntimeException {

    private Integer errorCode = ResultEnum.ERROR.getCode();

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BlogException(String msg) {
        super(msg);
    }

    public BlogException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
