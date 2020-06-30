package cn.codexing.blog.controller;


import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.entity.Category;
import cn.codexing.blog.service.CategoryService;
import cn.codexing.blog.vo.CategoryVO;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("添加分类")
    @PostMapping("/save")
    public Result<Object> saveCategory(@RequestBody Category category) {
        categoryService.save(category);
        return new Result<>("保存成功");
    }

    @ApiOperation("根据id删除分类")
    @PutMapping("/delete/{id}")
    public Result<Object> deleteCategory(@PathVariable Integer id) {
        int result = categoryService.deleteById(id);
        if (result==0){
            return new Result<>("请先移除该分类下的文章再删除该分类！！！");
        }
        return new Result<>("删除成功");
    }

    @ApiOperation("修改分类信息")
    @PutMapping("/update")
    public Result<Object> updateCategory(@RequestBody Category category) {
        categoryService.updateById(category);
        return new Result<>("修改成功");
    }

    @ApiOperation("分页查询分类")
    @PostMapping("/list")
    public Result<PageUtils<CategoryVO>> getByPage(@RequestBody PageUtils<CategoryVO> pages) {
        PageUtils<CategoryVO> page = categoryService.listCategory(pages);
        return new Result<>(page);
    }

    @ApiOperation("根据id查询分类信息")
    @GetMapping("/{id}")
    public Result<Category> getById(@PathVariable Integer id) {
        Category category = categoryService.getById(id);
        return new Result<>(category);
    }
}
