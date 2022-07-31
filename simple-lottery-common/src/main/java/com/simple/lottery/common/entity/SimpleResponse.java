package com.simple.lottery.common.entity;

import com.simple.lottery.common.enums.ResponseCode;

/**
 * 返回值
 *
 * @param <T>
 * @author wcx
 */
public class SimpleResponse<T> extends AirResponse<T> {

    public SimpleResponse(T data) {
        this.setData(data);
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getInfo();
        this.returnCode(ResponseCode.SUCCESS.getCode());
        this.returnMsg(ResponseCode.SUCCESS.getInfo());
    }

    public SimpleResponse() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getInfo();
        this.returnCode(ResponseCode.SUCCESS.getCode());
        this.returnMsg(ResponseCode.SUCCESS.getInfo());
    }

    public SimpleResponse(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public static <T> SimpleResponse<T> error(String code, String msg) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(code);
        response.returnMsg(msg);
        response.setStatus(Boolean.FALSE);
        return response;
    }

    public static <T> SimpleResponse<T> errorCode(String code) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(code);
        response.returnMsg("操作失败");
        response.setStatus(Boolean.FALSE);
        return response;
    }

    public static <T> SimpleResponse<T> errorMsg(String msg) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(ResponseCode.UN_ERROR.getCode());
        response.returnMsg(msg);
        response.setStatus(Boolean.FALSE);
        return response;
    }

    public static <T> SimpleResponse<T> success() {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(ResponseCode.SUCCESS.getCode());
        response.returnMsg(ResponseCode.SUCCESS.getInfo());
        response.setStatus(Boolean.TRUE);
        return response;
    }

    public static <T> SimpleResponse<T> success(T data, String code, String msg) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(code);
        response.returnMsg(msg);
        response.setStatus(Boolean.TRUE);
        response.setData(data);
        return response;
    }

    public static <T> SimpleResponse<T> successEn(T data, String code) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(code);
        response.returnMsg("Success");
        response.setStatus(Boolean.TRUE);
        response.setData(data);
        return response;
    }

    public static <T> SimpleResponse<T> successCn(T data, String code) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.returnCode(code);
        response.returnMsg("成功");
        response.setStatus(Boolean.TRUE);
        response.setData(data);
        return response;
    }

    public SimpleResponse<T> returnCode(String returnCode) {
        this.code = returnCode;
        return this;
    }

    public SimpleResponse<T> returnMsg(String returnMsg) {
        this.message = returnMsg;
        return this;
    }
}
