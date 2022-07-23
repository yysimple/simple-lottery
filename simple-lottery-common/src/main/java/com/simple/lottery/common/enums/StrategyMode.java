package com.simple.lottery.common.enums;

import lombok.Getter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖策略模式：总体概率、单项概率
 * 场景：两种抽奖算法描述，场景A20%、B30%、C50%
 * 单项概率：如果A奖品抽空后，B和C保持目前中奖概率，用户抽奖扔有20%中为A，因A库存抽空则结果展示为未中奖。为了运营成本，通常这种情况的使用的比较多
 * 总体概率：如果A奖品抽空后，B和C奖品的概率按照 3:5 均分，相当于B奖品中奖概率由 0.3 升为 0.375
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:44
 **/
@Getter
public enum StrategyMode {

    /**
     * 单项概率：如果A奖品抽空后，B和C保持目前中奖概率，用户抽奖扔有20%中为A，因A库存抽空则结果展示为未中奖。为了运营成本，通常这种情况的使用的比较多
     */
    SINGLE(1, "单项概率"),

    /**
     * 总体概率：如果A奖品抽空后，B和C奖品的概率按照 3:5 均分，相当于B奖品中奖概率由 0.3 升为 0.375
     */
    ENTIRETY(2, "总体概率");

    private final Integer code;
    private final String info;

    StrategyMode(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}

