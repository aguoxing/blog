package cn.codexing.blog.service;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.Category;
import cn.codexing.blog.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    PageUtils<CategoryVO> listCategory(PageUtils<CategoryVO> pages);

    /**
     * 根据id删除
     * @param id
     */
    int deleteById(Integer id);
}
