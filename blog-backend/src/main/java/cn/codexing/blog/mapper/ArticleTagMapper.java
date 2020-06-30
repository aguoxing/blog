package cn.codexing.blog.mapper;

import cn.codexing.blog.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Component
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    /**
     * 根据文章id查询
     * @param id
     * @return
     */
    List<ArticleTag> getByArticleId(String id);

    /**
     * 根据文章id删除
     * @param id
     */
    void deleteByArticleId(String id);

    /**
     * 根据标签id查询
     * @param id
     * @return
     */
    List<ArticleTag> getByTagId(Integer id);
}
