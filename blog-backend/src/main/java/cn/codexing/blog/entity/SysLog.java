package cn.codexing.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 访问状态，1正常0异常
     */
    private Integer status;

    /**
     * 异常消息
     */
    private String message;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 响应时间
     */
    private Long usedTime;

    /**
     * 返回值
     */
    private String result;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
