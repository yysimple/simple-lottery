package com.simple.lottery.common.entity;

import com.simple.lottery.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 状态判断值
 *
 * @author: WuChengXing
 * @create: 2022-07-31 17:00
 **/
public class Result implements Serializable  {
    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;

    public static Result buildResult(ResponseCode code) {
        return new Result(code.getCode(), code.getInfo());
    }

    public static Result buildResult(ResponseCode code, String info) {
        return new Result(code.getCode(), info);
    }

    public static Result buildResult(String code, String info) {
        return new Result(code, info);
    }

    public static Result buildResult(ResponseCode code, ResponseCode info) {
        return new Result(code.getCode(), info.getInfo());
    }

    public static Result buildSuccessResult() {
        return new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(ResponseCode.UN_ERROR.getCode(), ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info) {
        return new Result(ResponseCode.UN_ERROR.getCode(), info);
    }

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
