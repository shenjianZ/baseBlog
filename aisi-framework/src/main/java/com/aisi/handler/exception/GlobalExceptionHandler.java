package com.aisi.handler.exception;

import com.aisi.domain.ResponseResult;
import com.aisi.enums.AppHttpCodeEnum;
import com.aisi.exception.AiSiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/26 7:59
 * @Description:
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //TODO 异常处理

    @ExceptionHandler(AiSiException.class)
    public ResponseResult AiSiExceptionHandler(AiSiException e) {
        log.error("AiSiExceptionHandler:{}", e.getMessage());
        // 从异常对象中获取错误码和错误信息 封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e) {
        log.error("AiSiExceptionHandler:{}", e.getMessage());
        // 从异常对象中获取错误码和错误信息 封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
