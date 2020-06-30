package cn.codexing.blog.service;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.Tag;
import cn.codexing.blog.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface TagService extends IService<Tag> {

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    PageUtils<TagVO> listTagVo(PageUtils<TagVO> pages);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
