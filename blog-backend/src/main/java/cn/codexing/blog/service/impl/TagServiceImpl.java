package cn.codexing.blog.service.impl;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.ArticleTag;
import cn.codexing.blog.entity.Tag;
import cn.codexing.blog.mapper.ArticleTagMapper;
import cn.codexing.blog.mapper.TagMapper;
import cn.codexing.blog.service.TagService;
import cn.codexing.blog.vo.TagVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    public PageUtils<TagVO> listTagVo(PageUtils<TagVO> pages) {
        Page<TagVO> page = new Page<>(pages.getCurrPage(), pages.getPageSize());
        List<TagVO> tags = baseMapper.getByPage(page, pages);
        page.setRecords(tags);
        return new PageUtils<>(page);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        List<ArticleTag> articleTag = articleTagMapper.getByTagId(id);
        if (!articleTag.isEmpty() && articleTag != null) {
            return 0;
        }
        baseMapper.deleteById(id);
        return 1;
    }
}
