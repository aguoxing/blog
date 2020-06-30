package cn.codexing.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 点赞实体类
 * @Author: guoxing
 * @Date: 2020/4/14 15:25
 * @Email: 2933575030@qq.com
 */
@Data
public class ArticleLikes implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private String articleId;
}
