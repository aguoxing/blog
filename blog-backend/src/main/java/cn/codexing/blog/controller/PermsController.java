package cn.codexing.blog.controller;


import cn.codexing.blog.common.Result;
import cn.codexing.blog.entity.SysPerms;
import cn.codexing.blog.service.SysPermsService;
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
@RequestMapping("/perms")
public class PermsController {

    @Autowired
    private SysPermsService permsService;

    @ApiOperation("添加权限信息")
    @PostMapping("/save")
    public Result<Object> savePerms(@RequestBody SysPerms perms) {
        permsService.save(perms);
        return new Result<>("添加成功");
    }

    @ApiOperation("删除权限信息")
    @DeleteMapping("/delete/{id}")
    public Result<Object> deletePerms(@PathVariable Integer id) {
        permsService.removeById(id);
        return new Result<>("删除成功");
    }

    @ApiOperation("修改权限信息")
    @PutMapping("/update")
    public Result<Object> updatePerms(@RequestBody SysPerms perms) {
        permsService.updateById(perms);
        return new Result<>("修改成功");
    }

    @ApiOperation("查询所有权限信息")
    @GetMapping("/list")
    public Result<List<SysPerms>> listPerms() {
        List<SysPerms> perms = permsService.list();
        return new Result<>(perms);
    }

    @ApiOperation("根据id查询权限信息")
    @GetMapping("/{id}")
    public Result<SysPerms> getById(@PathVariable Integer id) {
        SysPerms perms = permsService.getById(id);
        return new Result<>(perms);
    }
}
