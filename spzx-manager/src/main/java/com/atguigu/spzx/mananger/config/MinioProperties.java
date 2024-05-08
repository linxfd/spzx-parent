package com.atguigu.spzx.mananger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix="spzx.minio") //读取节点
public class MinioProperties {

    // minio服务器的域名和端口
    private String endpointUrl;
    // 访问minio服务器的用户名
    private String accessKey;
    // 访问minio服务器的密码
    private String secreKey;
    // 存储桶名称
    private String bucketName;

}