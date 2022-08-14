package com.simple.lottery.app.mq.producer;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.constant.MqConstant;
import com.simple.lottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.simple.lottery.domain.activity.model.vo.InvoiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: kafka的生产者
 *
 * @author: WuChengXing
 * @create: 2022-08-14 16:10
 **/
@Component
public class SimpleKafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(SimpleKafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送中奖物品发货单消息
     *
     * @param invoice 发货单
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoice) {
        String objJson = JSON.toJSONString(invoice);
        logger.info("发送MQ消息(中奖发货单) topic：{} bizId：{} message：{}", MqConstant.TOPIC_INVOICE, invoice.getUId(), objJson);
        return kafkaTemplate.send(MqConstant.TOPIC_INVOICE, objJson);
    }

    /**
     * 发送领取活动记录MQ
     *
     * @param activityPartakeRecord 领取活动记录
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryActivityPartakeRecord(ActivityPartakeRecordVO activityPartakeRecord) {
        String objJson = JSON.toJSONString(activityPartakeRecord);
        logger.info("发送MQ消息(领取活动记录) topic：{} bizId：{} message：{}", MqConstant.TOPIC_ACTIVITY_PARTAKE, activityPartakeRecord.getUId(), objJson);
        return kafkaTemplate.send(MqConstant.TOPIC_ACTIVITY_PARTAKE, objJson);
    }
}
