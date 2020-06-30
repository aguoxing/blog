package cn.codexing.blog.service.impl;

import cn.codexing.blog.common.enums.ResultEnum;
import cn.codexing.blog.common.exception.BlogException;
import cn.codexing.blog.common.util.IPUtils;
import cn.codexing.blog.entity.SysUser;
import cn.codexing.blog.mapper.SysUserMapper;
import cn.codexing.blog.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据用户id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public SysUser getUserInfo(Integer id) {
        return baseMapper.getUserInfo(id);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(SysUser user, HttpServletRequest request) {
        // 先根据用户名查询用户是否存在
        SysUser u = baseMapper.getUserInfo(user.getId());
        // 如果存在，提示已存在
        if (u != null) {
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(), "用户已存在！");
        }
        // 如果不存在，插入数据
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIp(IPUtils.getIpAddr(request));
        baseMapper.insert(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void updateUserInfo(SysUser user) {
        SysUser oldUser = baseMapper.selectById(user.getId());
        String oldPwd = oldUser.getPassword();
        String newPwd = user.getPassword();
        if (!oldPwd.equals(newPwd)){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        baseMapper.updateById(user);
    }

    /**
     * 删除用户账号
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        baseMapper.deleteUser(id);
    }
}
