package com.atguigu.spzx.mananger;

import com.atguigu.spzx.mananger.properties.UserAuthProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


/**
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu.spzx"})
public class ManagerApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ManagerApplication.class, args);
    }
}
