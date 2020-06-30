package cn.codexing.blog.mapper;

import cn.codexing.blog.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户id获取权限
     * @param id
     * @return
     */
    List<SysRole> listRoleByUserId(Integer id);
}
