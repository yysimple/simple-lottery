package com.simple.lottery.app.mq.consumer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.constant.MqConstant;
import com.simple.lottery.common.enums.AwardState;
import com.simple.lottery.domain.activity.model.vo.InvoiceVO;
import com.simple.lottery.domain.award.model.request.GoodsRequest;
import com.simple.lottery.domain.award.model.result.DistributionResult;
import com.simple.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.simple.lottery.domain.award.service.goods.IDistributionGoods;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 中奖发货单监听消息
 *
 * @author: WuChengXing
 * @create: 2022-08-14 16:18
 **/
@Component
public class LotteryInvoiceListener {

    private final Logger logger = LoggerFactory.getLogger(LotteryInvoiceListener.class);

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @KafkaListener(topics = MqConstant.TOPIC_INVOICE, groupId = MqConstant.SIMPLE_LOTTERY_GROUP)
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());

        // 1. 判断消息是否存在
        if (!message.isPresent()) {
            return;
        }

        // 2. 处理 MQ 消息
        try {
            // 1. 转化对象（或者你也可以重写Serializer<T>）
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);

            // 2. 获取发送奖品工厂，执行发奖
            IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            DistributionResult distributionRes = distributionGoodsService.doDistribution(new GoodsRequest(invoiceVO.getUId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(), invoiceVO.getAwardName(), invoiceVO.getAwardContent()));

            Assert.isTrue(AwardState.SUCCESS.getCode().equals(distributionRes.getCode()), distributionRes.getInfo());

            // 3. 打印日志
            logger.info("消费MQ消息，完成 topic：{} bizId：{} 发奖结果：{}", topic, invoiceVO.getUId(), JSON.toJSONString(distributionRes));

            // 4. 消息消费完成
            ack.acknowledge();
        } catch (Exception e) {
            // 发奖环节失败，消息重试。所有到环节，发货、更新库，都需要保证幂等。
            logger.error("消费MQ消息，失败 topic：{} message：{}", topic, message.get());
            throw e;
        }
    }
}
