package com.simple.lottery.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: mq的初始化相关
 *
 * @author: WuChengXing
 * @create: 2022-08-14 15:51
 **/
@Configuration
public class MqInit {
    private final KafkaTopicProperties topicConfig;
    private final GenericWebApplicationContext context;

    public MqInit(KafkaTopicProperties topicConfig, GenericWebApplicationContext genericContext) {
        this.topicConfig = topicConfig;
        this.context = genericContext;
    }

    /**
     * 项目启动时进行初始化
     **/
    @PostConstruct
    public void init() {
        initializeBeans(topicConfig.getTopics());
    }

    private void initializeBeans(List<KafkaTopicProperties.Topic> topics) {
        topics.forEach(
                t -> context.registerBean(t.name, NewTopic.class, t::toNewTopic)
        );
    }


}
