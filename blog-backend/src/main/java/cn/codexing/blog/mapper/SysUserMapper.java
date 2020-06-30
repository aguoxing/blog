package cn.codexing.blog.mapper;

import cn.codexing.blog.entity.SysUser;
import cn.codexing.blog.security.JwtUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Component
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    JwtUser getByUsername(String username);

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    SysUser getUserInfo(Integer id);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);
}
