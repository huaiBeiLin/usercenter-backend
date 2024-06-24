package com.example.usercenter.exception;

import lombok.Data;

/**
 * packageName com.example.usercenter.Common
 *
 * @author 你的名字
 * @version JDK 8
 * @className BusinessException (此处以class为例)
 * @date 2024/6/24
 * @description TODO
 */
@Data
public class BusinessException extends RuntimeException {
    private int code;
    private String description;

    public BusinessException(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(String message, Throwable cause, int code, String description) {
        super(message, cause);
        this.code = code;
        this.description = description;
    }

    public BusinessException(Throwable cause, int code, String description) {
        super(cause);
        this.code = code;
        this.description = description;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String description) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.description = description;
    }
}
