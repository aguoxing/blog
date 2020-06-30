package cn.codexing.blog.controller;


import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.Tag;
import cn.codexing.blog.service.TagService;
import cn.codexing.blog.vo.TagVO;
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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("添加标签")
    @PostMapping("/save")
    public Result<Object> saveTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return new Result<>("保存成功");
    }

    @ApiOperation("根据id删除标签")
    @PutMapping("/delete/{id}")
    public Result<Object> deleteTag(@PathVariable Integer id) {
        int result = tagService.deleteById(id);
        if (result == 0){
            return new Result<>("请先移除该标签下的文章再删除！！！");
        }
        return new Result<>("删除成功");
    }

    @ApiOperation("更新标签")
    @PutMapping("/update")
    public Result<Object> updateTag(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return new Result<>("更新成功");
    }

    @ApiOperation("分页查询标签")
    @PostMapping("/list")
    public Result<PageUtils<TagVO>> getByPage(@RequestBody PageUtils<TagVO> pages) {
        PageUtils<TagVO> page = tagService.listTagVo(pages);
        return new Result<>(page);
    }

    @ApiOperation("根据id查询标签信息")
    @GetMapping("/{id}")
    public Result<Tag> getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        return new Result<>(tag);
    }
}
