package com.simple.lottery.app.process.draw.impl;

import com.simple.lottery.app.process.draw.IActivityDrawProcess;
import com.simple.lottery.app.process.draw.request.DrawProcessRequest;
import com.simple.lottery.app.process.draw.result.DrawProcessResult;
import com.simple.lottery.app.process.draw.result.RuleQuantificationCrowdResult;
import com.simple.lottery.common.entity.Result;
import com.simple.lottery.common.enums.*;
import com.simple.lottery.domain.activity.model.request.PartakeRequest;
import com.simple.lottery.domain.activity.model.result.PartakeResult;
import com.simple.lottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.simple.lottery.domain.activity.model.vo.DrawOrderVO;
import com.simple.lottery.domain.activity.model.vo.InvoiceVO;
import com.simple.lottery.domain.activity.service.partake.IActivityPartake;
import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.model.result.EngineResult;
import com.simple.lottery.domain.rule.service.engine.EngineFilter;
import com.simple.lottery.domain.strategy.model.request.DrawRequest;
import com.simple.lottery.domain.strategy.model.result.DrawResult;
import com.simple.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.simple.lottery.domain.strategy.service.draw.IDrawExec;
import com.simple.lottery.domain.support.ids.IdGenerator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动抽奖流程编排
 *
 * @author: WuChengXing
 * @create: 2022-08-02 18:54
 **/
@Service
public class ActivityDrawProcessImpl implements IActivityDrawProcess {

    private final Logger logger = LoggerFactory.getLogger(ActivityDrawProcessImpl.class);

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource(name = "ruleEngineHandle")
    private EngineFilter engineFilter;

    @Resource
    private Map<Ids, IdGenerator> idGeneratorMap;

//    @Resource
//    private KafkaProducer kafkaProducer;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessRequest req) {
        // 1. 领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeRequest(req.getUId(), req.getActivityId()));
        if (!ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        // 2. 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawRequest(req.getUId(), strategyId, String.valueOf(takeId)));
        if (DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(ResponseCode.LOSING_DRAW.getCode(), ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardVO drawAwardInfo = drawResult.getDrawAwardInfo();

        // 3. 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardInfo));

        // 4. 发送MQ，触发发奖流程

        // 5. 返回结果
        return new DrawProcessResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), drawAwardInfo);
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessRequest req, Long strategyId, Long takeId, DrawAwardVO drawAwardVO) {
        long orderId = idGeneratorMap.get(Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setUId(req.getUId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardVO.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardVO.getGrantType());
        drawOrderVO.setGrantDate(drawAwardVO.getGrantDate());
        drawOrderVO.setGrantState(GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardVO.getAwardId());
        drawOrderVO.setAwardType(drawAwardVO.getAwardType());
        drawOrderVO.setAwardName(drawAwardVO.getAwardName());
        drawOrderVO.setAwardContent(drawAwardVO.getAwardContent());
        return drawOrderVO;
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterRequest req) {
        // 1. 量化决策
        EngineResult engineResult = engineFilter.process(req);

        if (!engineResult.isSuccess()) {
            return new RuleQuantificationCrowdResult(ResponseCode.RULE_ERR.getCode(), ResponseCode.RULE_ERR.getInfo());
        }

        // 2. 封装结果
        RuleQuantificationCrowdResult ruleQuantificationCrowdResult = new RuleQuantificationCrowdResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
        ruleQuantificationCrowdResult.setActivityId(Long.valueOf(engineResult.getNodeValue()));

        return ruleQuantificationCrowdResult;
    }

    private InvoiceVO buildInvoiceVO(DrawOrderVO drawOrderVO) {
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setUId(drawOrderVO.getUId());
        invoiceVO.setOrderId(drawOrderVO.getOrderId());
        invoiceVO.setAwardId(drawOrderVO.getAwardId());
        invoiceVO.setAwardType(drawOrderVO.getAwardType());
        invoiceVO.setAwardName(drawOrderVO.getAwardName());
        invoiceVO.setAwardContent(drawOrderVO.getAwardContent());
        invoiceVO.setShippingAddress(null);
        invoiceVO.setExtInfo(null);
        return invoiceVO;
    }
}
