package cn.codexing.blog.vo;

import cn.codexing.blog.entity.Category;
import lombok.Data;

@Data
public class CategoryVO extends Category {
    /**
     * 该分类下的文章数量
     */
    private String articleCount;
}
