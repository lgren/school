package com.lgren.common.vo;

import java.util.HashMap;
import java.util.Map;

public class CommonResult<T> {
    //返回成功标识
    private boolean success;
    //信息
    private String message;
    //错误码
    private Integer errorCode;
    //返回数据
    private T data;

    public CommonResult() {

    }

    public CommonResult(T data) {
        this.success = true;
        this.data = data;
    }

    public CommonResult(String message, T data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public CommonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CommonResult(Integer errorCode, String message) {
        this.success = false;
        this.errorCode = errorCode;
        this.message = message;
    }

    public CommonResult(boolean success, Integer errorCode, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

    public CommonResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public CommonResult(boolean success, String message, Integer errorCode, T data) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
