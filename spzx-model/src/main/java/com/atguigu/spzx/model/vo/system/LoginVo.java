package com.atguigu.spzx.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录成功响应结果实体类")
public class LoginVo {

    /**
     * 令牌是uuid值，
     * 登录的时候将user:login:token作为key存到redis中，值是登录的用户信息
     * redisTemplate.opsForValue().set("user:login:" + token ,
     *        JSON.toJSONString(sysUser) , 30 , TimeUnit.MINUTES);
     */
    @Schema(description = "令牌")
    private String token ;

    @Schema(description = "刷新令牌,可以为空")
    private String refresh_token ;

}
