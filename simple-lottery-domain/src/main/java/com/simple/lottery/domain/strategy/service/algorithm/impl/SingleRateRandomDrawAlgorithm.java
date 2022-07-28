package com.simple.lottery.domain.strategy.service.algorithm.impl;

import com.simple.lottery.common.enums.StrategyMode;
import com.simple.lottery.domain.strategy.annotation.Strategy;
import com.simple.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:53
 **/
@Component("singleRateRandomDrawAlgorithm")
@Strategy(strategyMode = StrategyMode.SINGLE)
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 获取策略对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int randomVal = this.generateSecureRandomIntCode(100);
        int idx = super.hashIdx(randomVal);

        // 返回结果
        String awardId = rateTuple[idx];

        // 如果中奖ID命中排除奖品列表，则返回NULL
        if (excludeAwardIds.contains(awardId)) {
            return null;
        }

        return awardId;
    }

}
