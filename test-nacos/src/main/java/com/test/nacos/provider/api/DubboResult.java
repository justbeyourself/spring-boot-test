package com.test.nacos.provider.api;


import java.io.Serializable;

/**
 */
public class DubboResult<T> implements Serializable {

    public static final String SUCCESS = "0";

    /**
     * 操作成功,data为空
     **/
    public static final DubboResult OK = new DubboResult<>(SUCCESS);
    private static final long serialVersionUID = 4172473236047963783L;

    private String code;
    private String message;
    private T data;

    public DubboResult(String code) {
        this.code = code;
    }

    public DubboResult(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public DubboResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DubboResult(T data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(getCode());
    }

    public static <T> DubboResult<T> failure(String code, String message) {
        return new DubboResult<>(code, message, null);
    }

    public static <T> DubboResult<T> success() {
        return OK;
    }

    public static <T> DubboResult<T> success(T data) {
        return new DubboResult<>(SUCCESS, data);
    }

    public static DubboResult success(Object data, String msg) {
        return new DubboResult(SUCCESS, msg, data);
    }

    public static <T> DubboResult<T> ofSuccess(T data) {
        return new DubboResult<>(data);
    }

    public static <T> DubboResult<T> ofFailure(String code, String message) {
        return new DubboResult<>(code, message, null);
    }

    public static <T> DubboResult<T> ofFailure(String message) {
        return new DubboResult<>(null, message, null);
    }

    public static <T> DubboResult<T> ofFailure(Integer code, String message) {
        return new DubboResult<>(String.valueOf(code), message, null);
    }

    public static <T> DubboResult<T> of(T data, String code, String message) {
        return new DubboResult<>(code, message, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "DubboResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
