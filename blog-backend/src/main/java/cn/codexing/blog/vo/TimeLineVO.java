package cn.codexing.blog.vo;

import cn.codexing.blog.entity.Article;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TimeLineVO implements Serializable {
    private String year;

    private List<Article> articles;
}
