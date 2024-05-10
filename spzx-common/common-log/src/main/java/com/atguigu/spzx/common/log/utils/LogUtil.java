package com.atguigu.spzx.common.log.utils;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import com.atguigu.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @version 1.0
 */
public class LogUtil {

    //操作执行之后调用
    public static void afterHandlLog(Log sysLog, Object proceed,
                                     SysOperLog sysOperLog, int status ,
                                     String errorMsg) {
        if(sysLog.isSaveResponseData()) {
            sysOperLog.setJsonResult(JSON.toJSONString(proceed));
        }
        //设置操作状态，0正常 1异常
        sysOperLog.setStatus(status);
        //设置异常信息
        sysOperLog.setErrorMsg(errorMsg);
    }

    //操作执行之前调用
    public static void beforeHandleLog(Log sysLog,
                                       ProceedingJoinPoint joinPoint,
                                       SysOperLog sysOperLog) {
        // 设置操作模块名称
        sysOperLog.setTitle(sysLog.title());
        // 如果title为空，则获取Swagger的注解,用Operation中summary当操作模块名称
        //（1）获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if(sysLog.title().isEmpty()){
            //（2）获取方法上的注解对象
            Operation annotation = methodSignature.getMethod().getAnnotation(Operation.class);
            //（3）获取注解的参数值-- @Operation(summary = "修改角色接口")
            String summary = annotation.summary();
            // 设置操作模块名称
            sysOperLog.setTitle(summary);
        }

        // 设置操作类别，如OTHER其他,MANAGE后台用户,MOBILE手机端用户
        sysOperLog.setOperatorType(sysLog.operatorType().name());

        // 获取目标方法信息
        Method method = methodSignature.getMethod();
        sysOperLog.setMethod(method.getDeclaringClass().getName());

        // 获取请求相关参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 获得请求方式，如POST、GET
        sysOperLog.setRequestMethod(request.getMethod());
        // 设置请求地址，如/system/user/save
        sysOperLog.setOperUrl(request.getRequestURI());
        // 设置请求IP，0:0:0:0:0:0:0:1是ipv6的本地地址，就是127.0.0.1
        sysOperLog.setOperIp(request.getRemoteAddr());

        // 设置请求参数
        if(sysLog.isSaveRequestData()) {
            String requestMethod = sysOperLog.getRequestMethod();
            if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                String params = Arrays.toString(joinPoint.getArgs());
                // 设置请求参数
                sysOperLog.setOperParam(params);
            }
        }
        // 设置操作人员，从ThreadLocal变量中获取当前登录用户
        sysOperLog.setOperName(AuthContextUtil.get().getUserName());
    }
}