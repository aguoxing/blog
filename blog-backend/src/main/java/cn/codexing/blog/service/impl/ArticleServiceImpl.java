package cn.codexing.blog.service.impl;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.dto.ArticleDTO;
import cn.codexing.blog.entity.Article;
import cn.codexing.blog.entity.ArticleCategory;
import cn.codexing.blog.entity.ArticleTag;
import cn.codexing.blog.entity.Tag;
import cn.codexing.blog.mapper.ArticleCategoryMapper;
import cn.codexing.blog.mapper.ArticleMapper;
import cn.codexing.blog.mapper.ArticleTagMapper;
import cn.codexing.blog.service.*;
import cn.codexing.blog.vo.ArticleVO;
import cn.codexing.blog.vo.TimeLineVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ArticleCategoryService articleCategoryService;
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    /**
     * 分页查询
     *
     * @param pages
     * @return
     */
    @Override
    public PageUtils<ArticleVO> listArticleVo(PageUtils<ArticleVO> pages) {
        Page<ArticleVO> page = new Page<>(pages.getCurrPage(), pages.getPageSize());
        List<ArticleVO> articles = baseMapper.getByPage(page, pages);
        page.setRecords(articles);
        return new PageUtils<>(page);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public ArticleVO getArticleVoById(String id) {
        return baseMapper.getArticleVoById(id);
    }

    /**
     * 根据ID阅读
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleVO readArticleVoById(String id) {
        ArticleVO articleVO = baseMapper.getArticleVoById(id);
        baseMapper.updateReadTotal(id);
        return articleVO;
    }

    /**
     * 根据id删除文章
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(String id) {
        articleCategoryMapper.deleteByArticleId(id);
        articleTagMapper.deleteByArticleId(id);
        baseMapper.deleteById(id);
    }

    /**
     * 保存文章
     *
     * @param articleDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDTO articleDTO) {
        baseMapper.insert(articleDTO);
        // 关联文章分类
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleDTO.getId());
        articleCategory.setCategoryId(articleDTO.getCategory().getId());
        articleCategoryService.save(articleCategory);
        // 关联文章标签
        List<Tag> tags = articleDTO.getTagList();
        for (Tag tag : tags) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleDTO.getId());
            articleTag.setTagId(tag.getId());
            articleTagService.save(articleTag);
        }
    }

    /**
     * 更新文章
     *
     * @param articleDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleDTO articleDTO) {
        // 修改前先查询
        ArticleVO oldArticle = baseMapper.getArticleVoById(articleDTO.getId());

        // 修改
        LocalDateTime localDateTime = LocalDateTime.now();
        articleDTO.setUpdateTime(localDateTime);
        baseMapper.updateById(articleDTO);

        // 判断分类是否被修改
        Integer oldCategoryId = oldArticle.getCategory().getId();
        Integer newCategoryId = articleDTO.getCategory().getId();
        if (!oldCategoryId.equals(newCategoryId)) {
            ArticleCategory articleCategory = articleCategoryMapper.getByArticleId(articleDTO.getId());
            articleCategory.setCategoryId(newCategoryId);
            articleCategoryService.updateById(articleCategory);
        }

        // 判断标签是否被修改
        List<Tag> oldTags = oldArticle.getTagList();
        List<Tag> newTags = articleDTO.getTagList();
        if (!oldTags.equals(newTags)) {
            List<ArticleTag> articleTags = articleTagMapper.getByArticleId(articleDTO.getId());
            for (Tag tag : newTags) {
                for (ArticleTag articleTag : articleTags) {
                    articleTag.setTagId(tag.getId());
                    articleTagService.updateById(articleTag);
                }
            }
        }

    }

    /**
     * 更新文章评论数
     *
     * @param id
     */
    @Override
    public void updateCommentTotal(String id) {
        baseMapper.updateCommentTotal(id);
    }

    /**
     * 更新文章点赞数
     *
     * @param id
     */
    @Override
    public void updateLikeTotal(String id) {
        baseMapper.updateLikeTotal(id);
    }

    /**
     * 获取推荐阅读的文章
     *
     * @return
     */
    @Override
    public List<ArticleVO> getReCommendList() {
        return baseMapper.getReCommendList();
    }

    /**
     * 归档查询
     *
     * @return
     */
    @Override
    public List<TimeLineVO> archiveByYear() {
        List<TimeLineVO> timeLine = new ArrayList<>();
        List<String> years = baseMapper.findGroupYear();
        TimeLineVO timeLineVO = new TimeLineVO();
        for (String year : years) {
            timeLineVO.setYear(year);
            timeLineVO.setArticles(baseMapper.findByYear(year));
            timeLine.add(timeLineVO);
            timeLineVO = new TimeLineVO();
        }
        return timeLine;
    }

}
