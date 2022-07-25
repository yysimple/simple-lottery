package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.infrastructure.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动策略mapper
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:25
 **/
@Mapper
public interface StrategyMapper {
    /**
     * 查询策略配置
     *
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    Strategy queryStrategy(Long strategyId);

    /**
     * 插入策略配置
     *
     * @param strategy 策略配置
     */
    void insert(Strategy strategy);
}
