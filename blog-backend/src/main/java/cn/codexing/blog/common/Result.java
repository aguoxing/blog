package cn.codexing.blog.common;

import cn.codexing.blog.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: guoxing
 * @Date: 2020/3/18 9:07
 * @Email: 2933575030@qq.com
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(String msg) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(String msg, T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }
}
