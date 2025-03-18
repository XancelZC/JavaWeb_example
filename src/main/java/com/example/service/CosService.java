package com.example.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class CosService {
    @Value("${tencent.cos.bucket-name}")
    private String bucketName;
    @Value("${tencent.cos.base-url}")
    private String baseUrl;

    @Autowired
    private COSClient cosClient;

    public String uploadFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();

            // 处理空文件名或全空文件名
            if (originalFilename == null || originalFilename.trim().isEmpty()) {
                return UUID.randomUUID().toString();//返回一个随机的名字。
            }

            // 提取纯文件名,处理返回全路径的问题。
            String filename = Paths.get(originalFilename).getFileName().toString();

            // 提取扩展名
            int dotIndex = filename.lastIndexOf(".");
            String fileExt = (dotIndex <= 0) ? "" : filename.substring(dotIndex);
            String key = "uploads/" + UUID.randomUUID() + fileExt;

            // 上传至COS
            PutObjectRequest request = new PutObjectRequest(bucketName, key,
                    file.getInputStream(), null);
            cosClient.putObject(request);

            return baseUrl + "/" + key;
        } catch (Exception e) {
            log.error("文件上传失败: {}", e.getMessage());
            throw new RuntimeException("文件上传失败");
        }
    }
}
