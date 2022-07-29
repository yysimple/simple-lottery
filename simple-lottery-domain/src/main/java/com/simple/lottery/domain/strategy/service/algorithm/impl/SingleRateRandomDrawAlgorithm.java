package com.simple.lottery.domain.strategy.service.algorithm.impl;

import com.simple.lottery.common.enums.StrategyMode;
import com.simple.lottery.domain.strategy.annotation.Strategy;
import com.simple.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 普通的固定的策略，每次中奖概率都是固定的；当没有库存的情况下，则相当于是将该
 * 奖品进行过滤，然后返回null，或者一些其他的补偿
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
