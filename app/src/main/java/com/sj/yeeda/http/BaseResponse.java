package com.sj.yeeda.http;

/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:返回数据基础类
 */
public class BaseResponse<T> {

    private String code;
    private T data;
    private String exception;
    private String message;
    private int result;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", success=" + success +
                '}';
    }
}
