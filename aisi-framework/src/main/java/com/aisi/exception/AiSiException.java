package com.aisi.exception;

import com.aisi.enums.AppHttpCodeEnum;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/26 7:54
 * @Description:
 */


public class AiSiException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public AiSiException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}


