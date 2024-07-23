package com.example.usercenter.Common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 全局返回类
 * @param <T>
 */

public class BaseResponse<T> extends com.yupi.yucongming.dev.common.BaseResponse implements Serializable {
    int code;
    T data;
    String message = null;

    public BaseResponse(int code, T data, String message) {
        super(code, data, message);
    }

    public BaseResponse(int code, T data) {
        super(code, data);
    }
}
