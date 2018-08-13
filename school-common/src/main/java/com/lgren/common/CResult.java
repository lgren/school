package com.lgren.common;

/**
 * @Description 公共返回结果
 * @Author Lgren
 * @Date 2018/8/10 8:47
 */
public class CResult<T> {
    //返回成功标识
    private boolean success;
    //信息
    private String message;
    //错误码
    private Integer errorCode;
    //返回数据
    private T data;

    public static <T> CResult<T> newSuccess(T data) {
        CResult<T> successCResult = new CResult<>();
        successCResult.setSuccess(true);
        successCResult.setData(data);
        return successCResult;
    }

    public static <T> CResult<T> newFailure(String message) {
        return newFailure(null, message);
    }

    public static <T> CResult<T> newFailure(Integer errorCode, String message) {
        CResult<T> failureCResult = new CResult<>();
        failureCResult.setSuccess(false);
        failureCResult.setErrorCode(errorCode);
        failureCResult.setMessage(message);
        return failureCResult;
    }

    public CResult() {

    }

    public CResult(boolean success, String message, Integer errorCode, T data) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CResult{" +
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
