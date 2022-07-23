package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:47
 **/
@Getter
public enum AwardType {

    /**
     * 文字描述
     */
    DESC(1, "文字描述"),
    /**
     * 兑换码
     */
    RedeemCodeGoods(2, "兑换码"),
    /**
     * 优惠券
     */
    CouponGoods(3, "优惠券"),
    /**
     * 实物奖品
     */
    PhysicalGoods(4, "实物奖品");

    private final Integer code;
    private final String info;

    AwardType(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
