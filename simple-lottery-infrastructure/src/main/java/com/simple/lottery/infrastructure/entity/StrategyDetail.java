package com.simple.lottery.infrastructure.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动策略详情
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:18
 **/
@Data
public class StrategyDetail {
    /**
     * 自增ID
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
