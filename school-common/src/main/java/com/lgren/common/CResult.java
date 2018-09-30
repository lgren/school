package com.lgren.common;

/**
 * @description  公共返回结果
 * @author  Lgren
 * @date 2018/8/10 8:47
 */
public class CResult<T> {
    //返回成功标识 默认为false
    private boolean success;
    //错误码
    private Integer errorCode;
    //信息
    private String errorMsg;
    //返回数据
    private T data;

    public static <T> CResult<T> newSuccess(T data) {
        CResult<T> successCResult = new CResult<>();
        successCResult.setErrorCode(0);
        successCResult.setSuccess(true);
        successCResult.setData(data);
        return successCResult;
    }

    public static <T> CResult<T> newFailure(String message) {
        return newFailure(null, message);
    }

    public static <T> CResult<T> newFailure(Integer errorCode, String message) {
        CResult<T> failureCResult = new CResult<>();
        failureCResult.setErrorCode(errorCode);
        failureCResult.setErrorMsg(message);
        return failureCResult;
    }

    public CResult() {

    }

    public CResult(boolean success, String errorMsg, Integer errorCode, T data) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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
