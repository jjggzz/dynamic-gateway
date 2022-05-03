package com.jgz.gateway.support;

import org.springframework.http.HttpStatus;

public class ApiResult<T> {

    private int code;

    private String message;

    private T data;

    private ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> ApiResult<T> success() {
       return new ApiResult<>(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> ApiResult<T> fail() {
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public static <T> ApiResult<T> fail(String message) {
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),message, null);
    }

    public static <T> ApiResult<T> fail(int code,String message) {
        return new ApiResult<>(code,message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
