package cn.codexing.blog.service.impl;

import cn.codexing.blog.entity.ArticleComment;
import cn.codexing.blog.mapper.ArticleCommentMapper;
import cn.codexing.blog.service.ArticleCommentService;
import cn.codexing.blog.service.ArticleService;
import cn.codexing.blog.vo.CommentVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Autowired
    private ArticleService articleService;

    /**
     * 评论
     * @param comment
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void comment(ArticleComment comment) {
        baseMapper.insert(comment);
        articleService.updateCommentTotal(comment.getArticleId());
    }

    /**
     * 获取文章评论
     * @param id
     * @return
     */
    @Override
    public List<CommentVO> listCommentVo(String id) {
        List<CommentVO> result = new ArrayList<>();

        // 找出所有一级评论
        List<ArticleComment> parentComment = baseMapper.listByArticleId(id);

        for (ArticleComment comment : parentComment) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            // 找出一级评论下的子评论
            List<ArticleComment> reply1 = baseMapper.listById(comment.getId());
            // 找出子评论下的所有子评论，存放到临时集合
            getAllReply(reply1);
            commentVO.setReply(tempReplyList);
            result.add(commentVO);
            // 清除临时存放区
            tempReplyList = new ArrayList<>();
        }

        return result;
    }

    /**
     * 存放所有子评论的临时集合
     */
    private List<ArticleComment> tempReplyList = new ArrayList<>();

    /**
     * 找出所有子评论 递归迭代
     *
     * @param comments
     */
    private void getAllReply(List<ArticleComment> comments) {
        tempReplyList.addAll(comments);
        for (ArticleComment comment : comments) {
            List<ArticleComment> reply2 = baseMapper.listById(comment.getId());
            if (reply2.size() > 0) {
                getAllReply(reply2);
            }
        }
    }
}
