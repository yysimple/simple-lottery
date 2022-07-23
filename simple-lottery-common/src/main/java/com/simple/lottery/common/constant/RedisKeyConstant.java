package com.simple.lottery.common.constant;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:37
 **/
public class RedisKeyConstant {

    /**
     * 抽奖活动库存 Key
     */
    private static final String LOTTERY_ACTIVITY_STOCK_COUNT = "lottery_activity_stock_count_";

    public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT(Long activityId) {
        return LOTTERY_ACTIVITY_STOCK_COUNT + activityId;
    }

    /**
     * 抽奖活动库存锁 Key
     */
    private static final String LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN = "lottery_activity_stock_count_token_";

    public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(Long activityId, Integer stockUsedCount) {
        return LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN + activityId + "_" + stockUsedCount;
    }
}
