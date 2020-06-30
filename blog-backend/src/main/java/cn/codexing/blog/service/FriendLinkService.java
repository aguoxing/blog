package cn.codexing.blog.service;

import cn.codexing.blog.entity.FriendLink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface FriendLinkService extends IService<FriendLink> {

    /**
     * 查询已启用的友链
     * @return
     */
    List<FriendLink> listEnableLink();
}
