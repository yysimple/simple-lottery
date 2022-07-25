package com.simple.lottery.domain.strategy.annotation;

import com.simple.lottery.common.enums.StrategyMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖策略
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:07
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Strategy {

    /**
     * 策略模式
     *
     * @return
     */
    StrategyMode strategyMode();
}
