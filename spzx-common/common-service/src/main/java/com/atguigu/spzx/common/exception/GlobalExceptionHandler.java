package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 会自动捕获Exception异常
    @ExceptionHandler(Exception.class)
    // 将返回的数据以json数据返回
    public Result error(){
        return Result.build(null , ResultCodeEnum.SYSTEM_ERROR);
    }

    // 捕获抛出的自定义异常
    @ExceptionHandler(value = GuiguException.class)    // 处理自定义异常
    public Result error(GuiguException e) {
        e.printStackTrace();
        return Result.build(null , e.getResultCodeEnum()) ;
    }
}