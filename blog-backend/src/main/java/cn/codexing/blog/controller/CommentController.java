package cn.codexing.blog.controller;

import cn.codexing.blog.common.Result;
import cn.codexing.blog.entity.ArticleComment;
import cn.codexing.blog.service.ArticleCommentService;
import cn.codexing.blog.vo.CommentVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guoxing
 * @Date: 2020/4/14 14:44
 * @Email: 2933575030@qq.com
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ArticleCommentService commentService;

    @ApiOperation("评论某一文章")
    @PostMapping("/save")
    public Result<Object> saveComment(@RequestBody ArticleComment comment) {
        commentService.comment(comment);
        return new Result<>("评论成功");
    }

    @ApiOperation("根据文章id查询评论")
    @GetMapping("/list/{id}")
    public Result<List<CommentVO>> listCommentByArticleId(@PathVariable String id) {
        List<CommentVO> comments = commentService.listCommentVo(id);
        return new Result<>(comments);
    }

}
