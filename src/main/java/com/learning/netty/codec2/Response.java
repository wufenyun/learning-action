package com.learning.netty.codec2;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 11
 **/
public class Response {
    private String code;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}