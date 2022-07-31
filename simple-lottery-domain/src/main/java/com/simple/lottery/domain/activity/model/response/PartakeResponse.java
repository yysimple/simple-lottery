package com.simple.lottery.domain.activity.model.response;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动参与结果
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:49
 **/
@Data
public class PartakeResponse {

    /**
     * 策略ID
     */
    private Long strategyId;
    /**
     * 活动领取ID
     */
    private Long takeId;
    /**
     * 库存
     */
    private Integer stockCount;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;
}
