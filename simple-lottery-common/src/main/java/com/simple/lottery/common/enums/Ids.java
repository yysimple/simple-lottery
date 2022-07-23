package com.simple.lottery.common.enums;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: Ids 生成策略枚举
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:48
 **/
public enum Ids {

    /**
     * 雪花算法
     */
    SnowFlake,
    /**
     * 日期算法
     */
    ShortCode,
    /**
     * 随机算法
     */
    RandomNumeric;
}
