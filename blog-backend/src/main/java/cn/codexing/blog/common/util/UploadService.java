package cn.codexing.blog.common.util;

import cn.codexing.blog.common.config.UploadConfig;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: guoxing
 * @Date: 2020/4/6 14:48
 * @Email: 2933575030@qq.com
 */
@Component
@EnableConfigurationProperties(UploadConfig.class)
public class UploadService {
    private Log log = LogFactory.getLog(cn.codexing.blog.common.util.UploadService.class);

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadConfig uploadConfig;

    public String uploadImage(MultipartFile file) {
        // 1、校验文件类型
        String contentType = file.getContentType();
        if (!uploadConfig.getAllowTypes().contains(contentType)) {
            throw new RuntimeException("文件类型不支持");
        }

        try {
            // 3、上传到FastDFS
            // 3.1、获取扩展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 3.2、上传
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 返回路径
            return uploadConfig.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("【文件上传】上传文件失败！....{}", e);
            throw new RuntimeException("【文件上传】上传文件失败！" + e.getMessage());
        }
    }
}

