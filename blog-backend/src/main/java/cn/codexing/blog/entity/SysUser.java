package cn.codexing.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(0:未知,1:男,2:女)
     */
    private Integer gender;

    /**
     * 用户注册时间
     */
    private LocalDateTime userCreateTime;

    /**
     * 是否启用(0否，1是) 默认1
     */
    private Boolean enable;

    /**
     * 是否删除(0否，1是) 默认0
     */
    private Boolean deleted;


}
