package com.jydp.obqr.entity;


import com.jydp.obqr.net.IResponse;

/**
 * @author: yeq
 * @date: 2019/11/19
 */
public class ErrorBean implements IResponse {
    public int code;
    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
