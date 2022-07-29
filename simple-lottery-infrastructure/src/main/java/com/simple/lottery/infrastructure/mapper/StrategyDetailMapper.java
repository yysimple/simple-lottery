package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.infrastructure.entity.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动策略明细mapper
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:26
 **/
@Mapper
public interface StrategyDetailMapper {

    /**
     * 查询策略表详细配置
     *
     * @param strategyId 策略ID
     * @return 返回结果
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询无库存策略奖品ID
     *
     * @param strategyId 策略ID
     * @return 返回结果
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     *
     * @param strategyDetail 策略ID、奖品ID
     * @return 返回结果
     */
    int deductStock(StrategyDetail strategyDetail);

    /**
     * 插入策略配置组
     *
     * @param list 策略配置组
     */
    void insertList(List<StrategyDetail> list);

    /**
     * 获取并锁定指定策略的指定奖品的策略详情
     *
     * @param strategyId
     * @param awardId
     * @return
     */
    StrategyDetail getAndLockByStrategyIdAndAwardId(@Param("strategyId") Long strategyId, @Param("awardId") String awardId);

}
