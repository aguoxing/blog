package cn.codexing.blog.service;

import cn.codexing.blog.entity.ArticleComment;
import cn.codexing.blog.vo.CommentVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface ArticleCommentService extends IService<ArticleComment> {

    /**
     * 根据文章id获取文章评论
     * @param id
     * @return
     */
    List<CommentVO> listCommentVo(String id);

    /**
     * 评论
     * @param comment
     */
    void comment(ArticleComment comment);
}
