package com.example.demo.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseResult doException(Exception ex) {

        ex.printStackTrace();
        return ResponseResult.makeErrRsp("服务器异常，请稍后再试！");
    }
}