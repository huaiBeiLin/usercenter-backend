package com.example.usercenter.Common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 全局返回类
 * @param <T>
 */

@Data
public class BaseResponse<T> implements Serializable {
    int code;
    T data;
    String message = null;
    String description = null;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = "";
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.message = "";
        this.description = "";
    }
}
