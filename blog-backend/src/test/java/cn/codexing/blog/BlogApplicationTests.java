package cn.codexing.blog;

import cn.codexing.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class BlogApplicationTests {

    @Autowired
    private ArticleService articleService;
    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    @Test
    public void articleTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("{}",localDateTime);
    }

}
