package com.atguigu.spzx.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix="spzx.vcode") //读取节点
public class ValidateCodeProperties {

    // 短信验证码的AppCode
    private String appcode;

    // 专属签名模板ID，测试为CST_ptdie100,该模板ID仅为调试使用
    private String templateId;

}