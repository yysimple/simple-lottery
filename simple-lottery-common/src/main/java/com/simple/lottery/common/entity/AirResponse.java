package com.simple.lottery.common.entity;

import lombok.Data;

/**
 * 返回值
 *
 * @param <T>
 * @author wcx
 */
@Data
public class AirResponse<T> {
    protected Boolean status = true;
    protected Integer errorCode;
    protected String errorMessage;
    protected T data;
}