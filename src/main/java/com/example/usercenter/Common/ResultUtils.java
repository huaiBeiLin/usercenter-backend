package com.example.usercenter.Common;

import com.fasterxml.jackson.databind.ser.Serializers;

public class ResultUtils {
    /**
     * 工具类
     * @param data
     * @return
     * @author yuxin
     */
    
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(0, data, "ok");
    }

    public  static <T> BaseResponse<T> error(ErrorCode e) {
        return new BaseResponse<T>(e.getCode(), (T)"", e.getMessage());
    }

    public  static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, message, description);
    }
}
