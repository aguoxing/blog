package cn.codexing.blog.common.enums;

import lombok.Getter;

/**
 * @Author: guoxing
 * @Date: 2020/3/18 9:25
 * @Email: 2933575030@qq.com
 */
@Getter
public enum ResultEnum {

    SUCCESS(20000, "操作成功"),

    ERROR(400, "操作失败"),
    DATA_NOT_FOUND(401, "查询失败"),
    PARAMS_NULL(402, "参数不能为空"),
    PARAMS_ERROR(40005, "参数不合法！"),
    NOT_LOGIN(403, "未登录");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
