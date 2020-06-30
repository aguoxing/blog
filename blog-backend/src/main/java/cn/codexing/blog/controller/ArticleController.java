package cn.codexing.blog.controller;

import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.util.PageUtils;
import cn.codexing.blog.dto.ArticleDTO;
import cn.codexing.blog.entity.ArticleLikes;
import cn.codexing.blog.service.ArticleService;
import cn.codexing.blog.vo.ArticleVO;
import cn.codexing.blog.vo.TimeLineVO;
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
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 保存文章
     * @param articleDTO
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody ArticleDTO articleDTO) {
        articleService.saveArticle(articleDTO);
        return new Result<>("添加成功");
    }

    @ApiOperation("根据id删除文章")
    @PutMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable String id) {
        articleService.deleteArticle(id);
        return new Result<>("删除成功");
    }

    @ApiOperation("更新文章")
    @PutMapping("/update")
    public Result<Object> update(@RequestBody ArticleDTO articleDTO) {
        articleService.updateArticle(articleDTO);
        return new Result<>("更新成功");
    }

    @ApiOperation("分页查询文章信息")
    @PostMapping("/list")
    public Result<PageUtils<ArticleVO>> getByPage(@RequestBody PageUtils<ArticleVO> pages) {
        PageUtils<ArticleVO> page = articleService.listArticleVo(pages);
        return new Result<>(page);
    }

    @ApiOperation("获取推荐阅读的文章")
    @GetMapping("/recommend/list")
    public Result<List<ArticleVO>> listRecommend(){
        List<ArticleVO> articleVOS = articleService.getReCommendList();
        return new Result<>(articleVOS);
    }

    @ApiOperation("文章归档查询")
    @GetMapping("/archive/list")
    public Result<List<TimeLineVO>> listArchive(){
        List<TimeLineVO> timeLineVOS = articleService.archiveByYear();
        return new Result<>(timeLineVOS);
    }

    @ApiOperation("根据id获取文章信息")
    @GetMapping("/{id}")
    public Result<ArticleVO> get(@PathVariable String id) {
        ArticleVO articleVO = articleService.getArticleVoById(id);
        return new Result<>(articleVO);
    }

    @ApiOperation("根据id阅读文章")
    @GetMapping("/details/{id}")
    public Result<ArticleVO> read(@PathVariable String id) {
        ArticleVO articleVO = articleService.readArticleVoById(id);
        System.out.println(articleVO.getCreateTime());
        return new Result<>(articleVO);
    }

    @ApiOperation("点赞")
    @PostMapping("/like")
    public Result<Object> likeArticle(@RequestBody ArticleLikes articleLikes) {
        articleService.updateLikeTotal(articleLikes.getArticleId());
        return new Result<>("点赞成功");
    }

}
