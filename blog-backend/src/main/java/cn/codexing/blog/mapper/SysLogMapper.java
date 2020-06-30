package cn.codexing.blog.mapper;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.SysLog;
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
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * 分页查询
     * @param page
     * @param pages
     * @return
     */
    List<SysLog> getByPage(Page<SysLog> page, @Param("log") PageUtils<SysLog> pages);
}
