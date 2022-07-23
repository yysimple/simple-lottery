package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 发奖状态：0等待发奖、1发奖成功、2发奖失败
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:47
 **/
@Getter
public enum AwardState {

    /**
     * 等待发奖
     */
    WAIT(0, "等待发奖"),

    /**
     * 发奖成功
     */
    SUCCESS(1, "发奖成功"),

    /**
     * 发奖失败
     */
    FAILURE(2, "发奖失败");

    private final Integer code;
    private final String info;

    AwardState(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
