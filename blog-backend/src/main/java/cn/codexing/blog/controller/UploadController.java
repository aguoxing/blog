package cn.codexing.blog.controller;

import cn.codexing.blog.common.Result;
import cn.codexing.blog.common.util.QiniuCloudUtils;
import cn.codexing.blog.common.util.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private QiniuCloudUtils qiniuCloudUtils;

    @PostMapping("/qiniu")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<>("上传失败");
        }
        try {
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileExtend = originalFilename.substring(originalFilename.lastIndexOf("."));
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String fileKey = UUID.randomUUID().toString().replace("-", "") + fileExtend;
            return new Result<>("上传成功",qiniuCloudUtils.upload(fileInputStream,fileKey));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>("上传失败");
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/fdfs")
    public Result<String> uploadImage(MultipartFile file) {
        String url = uploadService.uploadImage(file);
        return new Result<>("上传成功！", url);
    }

}
