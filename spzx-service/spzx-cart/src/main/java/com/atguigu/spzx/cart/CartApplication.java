package com.atguigu.spzx.cart;

import com.atguigu.spzx.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // 排除数据库的自动化配置，Cart微服务不需要访问数据库
@EnableFeignClients(basePackages = {"com.atguigu.spzx.feign.product"}) // 开启Feign, 扫描Feign接口
@EnableUserWebMvcConfiguration   // 开启拦截器配置
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class , args) ;
    }

}