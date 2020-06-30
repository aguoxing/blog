package cn.codexing.blog.security;

import cn.codexing.blog.entity.SysRole;
import cn.codexing.blog.mapper.SysRoleMapper;
import cn.codexing.blog.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority > authorities = new HashSet<>();
        JwtUser user = sysUserMapper.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // 添加权限
        List<SysRole> roleList = sysRoleMapper.listRoleByUserId(user.getId());
        for (SysRole role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        user.setAuthorities(authorities);

        // 返回UserDetails实现类
        return user;
    }
}