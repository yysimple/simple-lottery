package com.simple.lottery.app.mq.producer;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.constant.MqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: kafka的生产者
 *
 * @author: WuChengXing
 * @create: 2022-08-14 14:05
 **/
@Component
public class KafkaProducerTest {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object obj) {
        String obj2String = JSON.toJSONString(obj);
        logger.info("准备发送消息为：{}", obj2String);

        // 发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(MqConstant.TOPIC_TEST, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                logger.info(MqConstant.TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                logger.info(MqConstant.TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
}
