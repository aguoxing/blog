package cn.codexing.blog.service;

import cn.codexing.blog.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户id查找用户
     *
     * @param id
     * @return
     */
    SysUser getUserInfo(Integer id);

    /**
     * 用户注册
     *
     * @param user
     */
    void register(SysUser user, HttpServletRequest request);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUserInfo(SysUser user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);
}
