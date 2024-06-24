package com.example.usercenter.exception;

import com.example.usercenter.Common.BaseResponse;
import com.example.usercenter.Common.ErrorCode;
import com.example.usercenter.Common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * packageName com.example.usercenter.exception
 *
 * @author 你的名字
 * @version JDK 8
 * @className GlobalExceptionHandler (此处以class为例)
 * @date 2024/6/24
 * @description TODO
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse BusinessExceptionHandler(BusinessException e) {
        return ResultUtils.error(e.getCode(), e.getMessage(), "");
    }
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse RuntimeExceptionHandler(RuntimeException e) {
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage(), "");
    }

}
