package cn.codexing.blog.service.impl;

import cn.codexing.blog.entity.FriendLink;
import cn.codexing.blog.mapper.FriendLinkMapper;
import cn.codexing.blog.service.FriendLinkService;
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
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    /**
     * 查询已启用的友链
     * @return
     */
    @Override
    public List<FriendLink> listEnableLink() {
        return baseMapper.listEnableLink();
    }
}
