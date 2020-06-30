package cn.codexing.blog.controller;

import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.SysLog;
import cn.codexing.blog.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private SysLogService logService;

    @ApiOperation("分页查询文章信息")
    @PostMapping("/list")
    public Result<PageUtils<SysLog>> getByPage(@RequestBody PageUtils<SysLog> pages) {
        PageUtils<SysLog> page = logService.listSysLog(pages);
        return new Result<>(page);
    }

    @ApiOperation("删除日志信息")
    @DeleteMapping("/delete/{id}")
    public Result<Object> deleteLog(@PathVariable Integer id) {
        logService.removeById(id);
        return new Result<>("删除成功");
    }

    @ApiOperation("根据id查询日志")
    @GetMapping("/{id}")
    public Result<SysLog> getById(@PathVariable Integer id) {
        SysLog log = logService.getById(id);
        return new Result<>(log);
    }
}
