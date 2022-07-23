package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:43
 **/
@Getter
public enum ActivityState {

    /**
     * 1：编辑
     */
    EDIT(1, "编辑"),
    /**
     * 2：提审
     */
    ARRAIGNMENT(2, "提审"),
    /**
     * 3：撤审
     */
    REVOKE(3, "撤审"),
    /**
     * 4：通过
     */
    PASS(4, "通过"),
    /**
     * 5：运行(活动中)
     */
    DOING(5, "运行(活动中)"),
    /**
     * 6：拒绝
     */
    REFUSE(6, "拒绝"),
    /**
     * 7：关闭
     */
    CLOSE(7, "关闭"),
    /**
     * 8：开启
     */
    OPEN(8, "开启");

    private final Integer code;
    private final String info;

    ActivityState(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
