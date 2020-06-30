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
public class ArticleComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 评论用户
     */
    private String user;

    /**
     * 被评论的文章id
     */
    private String articleId;

    /**
     * 父id(被回复的评论id)
     */
    private Integer parentId;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 发布日期
     */
    private LocalDateTime createTime;

}
