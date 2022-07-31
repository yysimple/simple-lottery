package com.simple.lottery.domain.activity.repository;

import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.domain.activity.model.vo.AwardVO;
import com.simple.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.simple.lottery.domain.activity.model.vo.StrategyVO;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-31 12:55
 **/
public interface IActivityRepository {

    /**
     * 添加活动配置
     *
     * @param activity 活动配置
     */
    void addActivity(ActivityVO activity);

    /**
     * 添加奖品配置集合
     *
     * @param awardList 奖品配置集合
     */
    void addAward(List<AwardVO> awardList);

    /**
     * 添加策略配置
     *
     * @param strategy 策略配置
     */
    void addStrategy(StrategyVO strategy);

    /**
     * 添加策略明细配置
     *
     * @param strategyDetailList 策略明细集合
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    /**
     * 变更活动状态
     *
     * @param activityId  活动ID
     * @param beforeState 修改前状态
     * @param afterState  修改后状态
     * @return 更新结果
     */
    boolean alterStatus(Long activityId, Enum<ActivityState> beforeState, Enum<ActivityState> afterState);
}
