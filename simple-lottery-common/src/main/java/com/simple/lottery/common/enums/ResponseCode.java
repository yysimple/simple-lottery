package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 响应值
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:29
 **/
@Getter
public enum ResponseCode {
    /**
     *
     */
    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    INDEX_DUP("0003", "主键冲突"),
    NO_UPDATE("0004", "SQL操作无更新"),
    LOSING_DRAW("D001", "未中奖"),
    RULE_ERR("D002", "量化人群规则执行失败"),
    NOT_CONSUMED_TAKE("D003", "未消费活动领取记录"),
    OUT_OF_STOCK("D004", "活动无库存"),
    ERR_TOKEN("D005", "分布式锁失败");

    private final String code;
    private final String info;

    ResponseCode(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
