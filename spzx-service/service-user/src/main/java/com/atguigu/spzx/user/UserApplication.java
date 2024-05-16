package com.atguigu.spzx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.atguigu.spzx"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}