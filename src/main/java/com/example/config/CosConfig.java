package com.example.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosConfig {
    @Value("${tencent.cos.secret-id}")   //Spring 框架提供的注解，用于从配置文件（如 application.yml 或 application.properties）中注入值到 Java 类的字段或方法参数中。​
    private String secretId;
    @Value("${tencent.cos.secret-key}")
    private String secretKey;
    @Value("${tencent.cos.region}")
    private String region;

    @Bean
    public COSClient cosClient() {
        // 1. 创建 COSClientConfig
        ClientConfig clientConfig = new ClientConfig(new Region(region));

        // 2. 创建 COSCredentials
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        // 3. 创建 COSClient
        return new COSClient(cred, clientConfig);
    }
}