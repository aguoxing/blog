package cn.codexing.blog.service.impl;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.ArticleCategory;
import cn.codexing.blog.entity.Category;
import cn.codexing.blog.mapper.ArticleCategoryMapper;
import cn.codexing.blog.mapper.CategoryMapper;
import cn.codexing.blog.service.CategoryService;
import cn.codexing.blog.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private ArticleCategoryMapper articleCategoryMapper;

    /**
     * 分页查询
     *
     * @param pages
     * @return
     */
    @Override
    public PageUtils<CategoryVO> listCategory(PageUtils<CategoryVO> pages) {
        Page<CategoryVO> page = new Page<>(pages.getCurrPage(), pages.getPageSize());
        List<CategoryVO> categories = baseMapper.getByPage(page, pages);
        page.setRecords(categories);
        return new PageUtils<>(page);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public int deleteById(Integer id) {
        List<ArticleCategory> articleCategory = articleCategoryMapper.getByCategoryId(id);
        if (!articleCategory.isEmpty()) {
            return 0;
        }
        baseMapper.deleteById(id);
        return 1;
    }
}
