package cn.codexing.blog.controller;


import cn.codexing.blog.common.Result;
import cn.codexing.blog.entity.SysRole;
import cn.codexing.blog.service.SysRoleService;
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
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result<Object> saveRoles(@RequestBody SysRole role) {
        roleService.save(role);
        return new Result<>("添加成功");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/delete/{id}")
    public Result<Object> deleteRoles(@PathVariable Integer id) {
        roleService.removeById(id);
        return new Result<>("删除成功");
    }

    @ApiOperation("修改角色信息")
    @PutMapping("/update")
    public Result<Object> updateRoles(@RequestBody SysRole role) {
        roleService.updateById(role);
        return new Result<>("修改成功");
    }

    @ApiOperation("查询所有角色信息")
    @GetMapping("/list")
    public Result<List<SysRole>> listRoles() {
        List<SysRole> roles = roleService.list();
        return new Result<>(roles);
    }

    @ApiOperation("根据id查询角色信息")
    @GetMapping("/{id}")
    public Result<SysRole> getById(@PathVariable Integer id) {
        SysRole role = roleService.getById(id);
        return new Result<>(role);
    }
}
