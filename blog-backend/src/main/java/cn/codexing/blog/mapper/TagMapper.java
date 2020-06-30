package cn.codexing.blog.mapper;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.Tag;
import cn.codexing.blog.vo.TagVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 分页查询
     *
     * @param page
     * @param pages
     * @return
     */
    List<TagVO> getByPage(Page<TagVO> page, @Param("tag") PageUtils<TagVO> pages);

    void deleteById(Integer id);
}
