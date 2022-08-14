package com.simple.lottery.common.constant;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: mq相关的常量
 *
 * @author: WuChengXing
 * @create: 2022-08-14 16:13
 **/
public final class MqConstant {

    /**
     * MQ主题：中奖发货单
     */
    public static final String TOPIC_INVOICE = "simple_lottery_invoice";

    /**
     * MQ主题：活动领取记录
     */
    public static final String TOPIC_ACTIVITY_PARTAKE = "simple_lottery_activity_partake";

    public static final String SIMPLE_LOTTERY_GROUP = "simple_lottery";

    /**
     * 测试相关的操作
     */
    public static final String TOPIC_TEST = "Hello-Kafka";

    public static final String TOPIC_TEST_GROUP = "test-consumer-group";
}
