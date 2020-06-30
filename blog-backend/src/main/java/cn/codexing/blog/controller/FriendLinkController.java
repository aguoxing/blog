package cn.codexing.blog.controller;


import cn.codexing.blog.common.Result;
import cn.codexing.blog.entity.FriendLink;
import cn.codexing.blog.service.FriendLinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/link")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @ApiOperation("添加友链")
    @PostMapping("/save")
    public Result<Object> saveLink(@RequestBody FriendLink friendLink) {
        friendLinkService.save(friendLink);
        return new Result<>("添加成功");
    }

    @ApiOperation("修改友链状态")
    @PutMapping("/update")
    public Result<Object> updateLink(@RequestBody FriendLink friendLink) {
        friendLinkService.updateById(friendLink);
        return new Result<>("修改成功");
    }

    @ApiOperation("查询所有友链")
    @GetMapping("/list")
    public Result<List<FriendLink>> listLink() {
        List<FriendLink> friendLinks = friendLinkService.list();
        return new Result<>(friendLinks);
    }

    @ApiOperation("查询所有已启用的友链")
    @GetMapping("/list/enable")
    public Result<List<FriendLink>> listEnableLink() {
        List<FriendLink> friendLinks = friendLinkService.listEnableLink();
        return new Result<>(friendLinks);
    }
}
