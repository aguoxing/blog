package cn.codexing.blog.mapper;

import cn.codexing.blog.entity.ArticleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    /**
     * 根据文章id获取评论信息
     * @param id
     * @return
     */
    List<ArticleComment> listByArticleId(String id);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    List<ArticleComment> listById(Integer id);
}
