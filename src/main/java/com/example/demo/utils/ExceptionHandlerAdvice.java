package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * 全局异常拦截器
 */

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    // 参数格式异常处理
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult badRequestException(IllegalArgumentException exception) {
        return ResponseResult.makeRsp(HttpStatus.BAD_REQUEST.value() + "", "参数格式不符！");
    }

    // 权限不足异常处理
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult badRequestException(AccessDeniedException exception) {
        return ResponseResult.makeRsp(HttpStatus.FORBIDDEN.value() + "", exception.getMessage());
    }

    // 参数缺失异常处理
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult badRequestException(Exception exception) {
        return ResponseResult.makeRsp(HttpStatus.BAD_REQUEST.value() + "", "缺少必填参数！");
    }

    // 空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleTypeMismatchException(NullPointerException ex) {
        return ResponseResult.makeRsp(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", "空指针异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleUnexpectedServer(Exception ex) {
        return ResponseResult.makeRsp(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", "系统发生异常，请联系管理员");
    }

    // 系统异常处理
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult exception(Throwable throwable) {
        return ResponseResult.makeRsp(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", "系统异常，请联系管理员！");
    }
}