package com.simple.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品概率信息，奖品编号、库存、概率
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardRateVO {

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;
}
