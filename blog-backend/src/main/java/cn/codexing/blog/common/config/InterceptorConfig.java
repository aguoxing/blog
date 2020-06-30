package cn.codexing.blog.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @Author: guoxing
 * @Date: 2020/4/6 14:37
 * @Email: 2933575030@qq.com
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(c -> c instanceof MappingJackson2HttpMessageConverter);
        converters.add(new MappingJackson2HttpMessageConverter(jacksonObjectMapperCustomization()));
    }

//    private static final String DATE_FORMAT = "yyyy-MM-dd";
//    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public ObjectMapper jacksonObjectMapperCustomization() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        format.setTimeZone(timeZone);

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
//                .simpleDateFormat(DATE_TIME_FORMAT)
//                .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
//                .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
                .timeZone(timeZone)
                .dateFormat(format);
        return builder.build();
    }

}
