package cn.codexing.blog.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {

    private String baseUrl;
    private List<String> allowTypes;

}