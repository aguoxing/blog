package cn.codexing.blog.dto;

import cn.codexing.blog.entity.Article;
import cn.codexing.blog.entity.Category;
import cn.codexing.blog.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @Author: guoxing
 * @Date: 2020/4/6 11:58
 * @Email: 2933575030@qq.com
 */
@Data
public class ArticleDTO extends Article {
    //所属分类
    private Category category;

    //所属标签
    private List<Tag> tagList;
}
