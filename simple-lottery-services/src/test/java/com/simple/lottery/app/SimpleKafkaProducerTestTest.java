package com.simple.lottery.app;

import com.simple.lottery.app.mq.producer.KafkaProducerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-14 14:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleKafkaProducerTestTest {

    private final Logger logger = LoggerFactory.getLogger(SimpleKafkaProducerTestTest.class);

    @Resource
    private KafkaProducerTest kafkaProducerTest;

    @Test
    public void testSend() throws InterruptedException {
        // 循环发送消息
        while (true) {
            kafkaProducerTest.send("你好，我是Lottery 001");
            Thread.sleep(3500);
        }
    }
}
