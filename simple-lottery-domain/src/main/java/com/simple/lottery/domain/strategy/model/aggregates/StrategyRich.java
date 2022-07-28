package com.simple.lottery.domain.strategy.model.aggregates;

import com.simple.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.simple.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖策略聚合对象
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRich {

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 策略配置
     */
    private StrategyBriefVO strategy;

    /**
     * 策略明细
     */
    private List<StrategyDetailBriefVO> strategyDetailList;
}
