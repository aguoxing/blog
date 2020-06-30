package cn.codexing.blog.service;

import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface SysLogService extends IService<SysLog> {
    /**
     * 分页查询
     *
     * @param
     * @return
     */
    PageUtils<SysLog> listSysLog(PageUtils<SysLog> pages);
}
