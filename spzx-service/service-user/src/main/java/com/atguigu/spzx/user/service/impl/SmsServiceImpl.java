package com.atguigu.spzx.user.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.atguigu.spzx.user.config.ValidateCodeProperties;
import com.atguigu.spzx.user.service.SmsService;
import com.atguigu.spzx.utils.HttpUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;


    @Autowired
    private ValidateCodeProperties validateCodeProperties ;
    @Override
    public void sendValidateCode(String phone) {
        // 判断是否已经发送验证码
        String code = redisTemplate.opsForValue().get("phone:code:" + phone);
        if(StringUtils.hasText(code)) {
            return;
        }

        // 生成验证码
        String validateCode = RandomStringUtils.randomNumeric(4);      // 生成验证码
        // 将验证码保存到redis中, 设置过期时间,5分钟
        redisTemplate.opsForValue().set("phone:code:" + phone , validateCode , 5 , TimeUnit.MINUTES);
        // 调用短信发送方法
        this.sendSms(phone , validateCode) ;
    }

    // 发送短信方法
    public void sendSms(String phone, String validateCode) {

        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = validateCodeProperties.getAppcode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+validateCode);
        bodys.put("template_id", validateCodeProperties.getTemplateId());  //注意，CST_ptdie100该模板ID仅为调试使用，调试结果为"status": "OK" ，即表示接口调用成功，然后联系客服报备自己的专属签名模板ID，以保证短信稳定下发
        bodys.put("phone_number", phone);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}