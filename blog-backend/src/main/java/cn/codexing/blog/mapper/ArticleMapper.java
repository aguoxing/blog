package cn.codexing.blog.mapper;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.Article;
import cn.codexing.blog.vo.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 分页查询
     *
     * @param page
     * @param pages
     * @return
     */
    List<ArticleVO> getByPage(Page<ArticleVO> page, @Param("blog") PageUtils<ArticleVO> pages);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ArticleVO getArticleVoById(String id);

    /**
     * 根据id删除文章
     *
     * @param id
     */
    void deleteById(String id);

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
     * 更新文章访问量
     *
     * @param id
     */
    void updateReadTotal(String id);

    /**
     * 获取推荐阅读的文章
     *
     * @return
     */
    List<ArticleVO> getReCommendList();

    /**
     * 归档查询
     *
     * @return
     */
    List<String> findGroupYear();

    List<Article> findByYear(String year);
}
