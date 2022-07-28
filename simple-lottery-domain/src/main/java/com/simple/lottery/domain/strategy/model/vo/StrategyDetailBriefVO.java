package com.simple.lottery.domain.strategy.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 策略明细简要信息
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:19
 **/
@Data
public class StrategyDetailBriefVO {

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品库存
     */
    private Integer awardCount;

    /**
     * 奖品剩余库存
     */
    private Integer awardSurplusCount;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;
}
