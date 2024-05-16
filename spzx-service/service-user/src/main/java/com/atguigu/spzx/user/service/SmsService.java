package com.atguigu.spzx.user.service;

/**
 * @version 1.0
 */
public interface SmsService {
    // 发送短信验证码
    void sendValidateCode(String phone);
}
