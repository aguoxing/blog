package cn.codexing.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 作者
     */
    private String author;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 封面
     */
    private String cover;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 评论总数
     */
    private Integer commentCount;

    /**
     * 点赞总数
     */
    private Integer likeCount;

    /**
     * 是否置顶(0:否,1:是)
     */
    private Boolean top;

    /**
     * 是否推荐阅读(0:否,1:是)
     */
    private Boolean recommend;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 是否启用(0否，1是) 默认1
     */
    private Boolean enable;

    /**
     * 是否删除(0否，1是) 默认0
     */
    private Boolean deleted;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

}
