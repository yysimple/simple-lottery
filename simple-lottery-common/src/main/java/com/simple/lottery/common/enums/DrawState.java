package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 中奖状态：0未中奖、1已中奖、2兜底奖
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:45
 **/
@Getter
public enum DrawState {

    /**
     * 未中奖
     */
    FAIL(0, "未中奖"),

    /**
     * 已中奖
     */
    SUCCESS(1, "已中奖"),

    /**
     * 兜底奖
     */
    Cover(2, "兜底奖");

    private final Integer code;
    private final String info;

    DrawState(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
