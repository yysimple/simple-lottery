package com.simple.lottery.domain.activity.model.aggregates;

import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.domain.activity.model.vo.AwardVO;
import com.simple.lottery.domain.activity.model.vo.StrategyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-30 23:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityConfigRich {

    /**
     * 活动配置
     */
    private ActivityVO activity;

    /**
     * 策略配置(含策略明细)
     */
    private StrategyVO strategy;

    /**
     * 奖品配置
     */
    private List<AwardVO> awardList;
}
