package com.simple.lottery.app.mq.consumer;

import com.simple.lottery.app.mq.producer.KafkaProducerTest;
import com.simple.lottery.common.constant.MqConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: kafka的消费者
 *
 * @author: WuChengXing
 * @create: 2022-08-14 14:05
 **/
@Component
public class KafkaConsumerTest {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerTest.class);

    @KafkaListener(topics = MqConstant.TOPIC_TEST, groupId = MqConstant.TOPIC_TEST_GROUP)
    public void topicTest(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            logger.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
