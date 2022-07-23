package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: mq发送状态
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:50
 **/
@Getter
public enum MQState {

    /**
     * mq发送状态
     */
    INIT(0, "初始"),
    COMPLETE(1, "完成"),
    FAIL(2, "失败");

    private final Integer code;
    private final String info;

    MQState(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
