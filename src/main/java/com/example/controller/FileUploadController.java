package com.example.controller;

import com.example.pojo.Result;
import com.example.service.CosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private CosService cosService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        try {
            // 校验文件类型（示例仅允许图片）
            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                return Result.error("仅支持图片格式");
            }

            String url = cosService.uploadFile(file);
            return Result.success(url);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
