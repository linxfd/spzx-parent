package com.atguigu.spzx.common.log.annotation;

import com.atguigu.spzx.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.0
 */
@Target({ElementType.METHOD})   //用来限制注解的使用范围
@Retention(RetentionPolicy.RUNTIME)   //用于指定该Annotation可以保留多长时间,注解只在源文件中保留，在编译期间删除
public @interface Log {		// 自定义操作日志记录注解

    public String title()  default "" ;								// 模块名称
    //OperatorType是一个枚举类  OTHER其他，MANAGE后台用户，MOBILE手机端用户
    public OperatorType operatorType() default OperatorType.MANAGE;	// 操作类别，默认为管理端
    public int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）
    public boolean isSaveRequestData() default true;   // 是否保存请求的参数
    public boolean isSaveResponseData() default true;  // 是否保存响应的参数
}