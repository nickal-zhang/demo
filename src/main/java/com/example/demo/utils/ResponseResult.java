package com.example.demo.utils;


import com.example.demo.core.RetCode;

import java.util.List;

/**
 * @Description: 返回对象实体
 */
public class ResponseResult<T> {
    public String code;
    private String msg;
    private T data;
    private final static String SUCCESS = "success";

    public String getCode() {
        return code;
    }

    public ResponseResult<T> setCode(RetCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public ResponseResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static <T> ResponseResult<T> makeOKRsp() {
        return new ResponseResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> ResponseResult<T> makeOKRsp(T data) {
        return new ResponseResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> ResponseResult<T> makeErrRsp(String message) {
        return new ResponseResult<T>().setCode(RetCode.FAIL).setMsg(message);
    }

    public static <T> ResponseResult<T> makeRsp(String code, String msg) {
        return new ResponseResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> ResponseResult<T> makeRsp(String code, String msg, T data) {
        return new ResponseResult<T>().setCode(code).setMsg(msg).setData(data);
    }

}
