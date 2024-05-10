package com.atguigu.spzx.mananger;

import com.atguigu.spzx.mananger.properties.UserAuthProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu.spzx"})
@EnableScheduling //开启定时任务
@EnableTransactionManagement //开启注解方式的事务管理
public class ManagerApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ManagerApplication.class, args);
    }
}
