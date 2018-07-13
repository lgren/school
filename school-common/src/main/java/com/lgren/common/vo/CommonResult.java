package com.lgren.common.vo;

public class CommonResult<T> {
    //返回成功标识
    private boolean success;
    //错误信息
    private String message;
    //返回数据
    private T data;

    public CommonResult() {

    }

    public CommonResult(T data) {
        this.success = true;
        this.data = data;
    }

    public CommonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CommonResult(boolean success, String message, T data) {

        this.success = success;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
