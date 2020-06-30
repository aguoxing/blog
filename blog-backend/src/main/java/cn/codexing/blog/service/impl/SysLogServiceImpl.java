package cn.codexing.blog.service.impl;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.SysLog;
import cn.codexing.blog.mapper.SysLogMapper;
import cn.codexing.blog.service.SysLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    /**
     * 分页查询
     *
     * @param
     * @return
     */
    @Override
    public PageUtils<SysLog> listSysLog(PageUtils<SysLog> pages){
        Page<SysLog> page = new Page<>(pages.getCurrPage(), pages.getPageSize());
        List<SysLog> sysLogs = baseMapper.getByPage(page, pages);
        page.setRecords(sysLogs);
        return new PageUtils<>(page);
    }
}
