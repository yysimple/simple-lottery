package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 发奖状态 0初始、1完成、2失败
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:50
 **/
@Getter
public enum GrantState {

    /**
     * 发奖状态 0初始、1完成、2失败
     */
    INIT(0, "初始"),
    COMPLETE(1, "完成"),
    FAIL(2, "失败");

    private Integer code;
    private String info;

    GrantState(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
