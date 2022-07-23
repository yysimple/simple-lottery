package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动单使用状态 0未使用、1已使用
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:49
 **/
@Getter
public enum TaskState {

    /**
     * 活动单使用状态 0未使用、1已使用
     */
    NO_USED(0, "未使用"),
    USED(1, "已使用");

    private final Integer code;
    private final String info;

    TaskState(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
