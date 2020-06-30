package cn.codexing.blog.common.enums;

import lombok.Getter;

/**
 * @Author: guoxing
 * @Date: 2020/3/18 9:25
 * @Email: 2933575030@qq.com
 */
@Getter
public enum StateEnum {

    /**
     * 删除状态
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态
     */
    ENABLE(1, "启用"),
    DISABLE(0, "未启用"),

    /**
     * 请求状态
     */
    REQUEST_SUCCESS(1,"请求正常"),
    REQUEST_ERROR(0,"请求异常"),

    /**
     * 用户标识。
     * 0表示管理员，1表示普通用户
     */
    ADMIN(0, "管理员"),
    USER(1, "普通用户"),


    /**
     * 性别状态
     */
    GENDER_MALE(1, "男"),
    GENDER_FEMALE(0, "女"),
    GENDER_UNKNOWN(2, "未知");


    private Integer code;
    private String msg;

    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
