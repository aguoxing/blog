package cn.codexing.blog.vo;

import cn.codexing.blog.entity.ArticleComment;
import lombok.Data;

import java.util.List;

/**
 * @Author: guoxing
 * @Date: 2020/4/14 14:25
 * @Email: 2933575030@qq.com
 */
@Data
public class CommentVO extends ArticleComment {
    private List<ArticleComment> Reply;
}
