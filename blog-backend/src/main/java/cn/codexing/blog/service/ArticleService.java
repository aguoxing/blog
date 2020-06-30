package cn.codexing.blog.service;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.dto.ArticleDTO;
import cn.codexing.blog.entity.Article;
import cn.codexing.blog.vo.ArticleVO;
import cn.codexing.blog.vo.TimeLineVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface ArticleService extends IService<Article> {
    /**
     * 分页查询
     *
     * @param
     * @return
     */
    PageUtils<ArticleVO> listArticleVo(PageUtils<ArticleVO> pages);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ArticleVO getArticleVoById(String id);

    /**
     * 根据id阅读文章
     * @param id
     * @return
     */
    ArticleVO readArticleVoById(String id);

    /**
     * 根据id删除文章
     * @param id
     */
    void deleteArticle(String id);

    /**
     * 保存文章
     *
     * @param articleDTO
     */
    void saveArticle(ArticleDTO articleDTO);

    /**
     * 更新文章
     *
     * @param articleDTO
     */
    void updateArticle(ArticleDTO articleDTO);

    /**
     * 更新文章评论总数
     *
     * @param id
     */
    void updateCommentTotal(String id);

    /**
     * 更新文章点赞总数
     *
     * @param id
     */
    void updateLikeTotal(String id);

    /**
     * 获取推荐阅读的文章
     * @return
     */
    List<ArticleVO> getReCommendList();

    /**
     * 归档查询
     * @return
     */
    List<TimeLineVO> archiveByYear();
}
