package cn.codexing.blog.vo;

import cn.codexing.blog.entity.Tag;
import lombok.Data;

@Data
public class TagVO extends Tag {
    /**
     * 给标签下的文章数量
     */
    private String articleCount;
}
