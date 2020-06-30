package cn.codexing.blog.mapper;

import cn.codexing.blog.entity.FriendLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
public interface FriendLinkMapper extends BaseMapper<FriendLink> {

    /**
     * 查询已启用的友链
     * @return
     */
    List<FriendLink> listEnableLink();
}
