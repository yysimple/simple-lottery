package com.simple.lottery.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: kafka的topic配置类
 *
 * @author: WuChengXing
 * @create: 2022-08-14 16:00
 **/
@Configuration
@ConfigurationProperties(prefix = "kafka")
@Setter
@Getter
@ToString
public class KafkaTopicProperties {

    private List<Topic> topics;

    @Setter
    @Getter
    @ToString
    public static class Topic {
        String name;
        Integer numPartitions = 3;
        Short replicationFactor = 1;
        NewTopic toNewTopic() {
            return new NewTopic(this.name, this.numPartitions, this.replicationFactor);
        }
    }

}
