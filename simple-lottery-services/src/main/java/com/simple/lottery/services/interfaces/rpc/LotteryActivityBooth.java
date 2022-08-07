package com.simple.lottery.services.interfaces.rpc;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.app.process.draw.IActivityDrawProcess;
import com.simple.lottery.app.process.draw.request.DrawProcessRequest;
import com.simple.lottery.app.process.draw.result.DrawProcessResult;
import com.simple.lottery.app.process.draw.result.RuleQuantificationCrowdResult;
import com.simple.lottery.common.enums.ResponseCode;
import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.simple.lottery.rpc.activity.booth.ILotteryActivityBooth;
import com.simple.lottery.rpc.activity.booth.dto.AwardDto;
import com.simple.lottery.rpc.activity.booth.request.DrawRequest;
import com.simple.lottery.rpc.activity.booth.request.QuantificationDrawRequest;
import com.simple.lottery.rpc.activity.booth.result.DrawResult;
import com.simple.lottery.services.interfaces.transfer.IMapping;
import com.simple.rpc.common.annotation.SimpleRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖活动展台
 *
 * @author: WuChengXing
 * @create: 2022-08-07 22:20
 **/
@SimpleRpcService
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private final Logger logger = LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityDrawProcess activityProcess;

    @Resource
    private IMapping<DrawAwardVO, AwardDto> awardMapping;

    @Override
    public DrawResult doDraw(DrawRequest drawReq) {
        try {
            logger.info("抽奖，开始 uId：{} activityId：{}", drawReq.getUId(), drawReq.getActivityId());

            // 1. 执行抽奖
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessRequest(drawReq.getUId(), drawReq.getActivityId()));
            if (!ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("抽奖，失败(抽奖过程异常) uId：{} activityId：{}", drawReq.getUId(), drawReq.getActivityId());
                return new DrawResult(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 2. 数据转换
            DrawAwardVO drawAwardVO = drawProcessResult.getDrawAwardVO();
            AwardDto awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(drawReq.getActivityId());

            // 3. 封装数据
            DrawResult drawRes = new DrawResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDto(awardDTO);

            logger.info("抽奖，完成 uId：{} activityId：{} drawRes：{}", drawReq.getUId(), drawReq.getActivityId(), JSON.toJSONString(drawRes));

            return drawRes;
        } catch (Exception e) {
            logger.error("抽奖，失败 uId：{} activityId：{} reqJson：{}", drawReq.getUId(), drawReq.getActivityId(), JSON.toJSONString(drawReq), e);
            return new DrawResult(ResponseCode.UN_ERROR.getCode(), ResponseCode.UN_ERROR.getInfo());
        }
    }

    @Override
    public DrawResult doQuantificationDraw(QuantificationDrawRequest quantificationDrawReq) {
        try {
            logger.info("量化人群抽奖，开始 uId：{} treeId：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId());

            // 1. 执行规则引擎，获取用户可以参与的活动号
            RuleQuantificationCrowdResult ruleQuantificationCrowdResult = activityProcess.doRuleQuantificationCrowd(new DecisionMatterRequest(quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId(), quantificationDrawReq.getValMap()));
            if (!ResponseCode.SUCCESS.getCode().equals(ruleQuantificationCrowdResult.getCode())) {
                logger.error("量化人群抽奖，失败(规则引擎执行异常) uId：{} treeId：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId());
                return new DrawResult(ruleQuantificationCrowdResult.getCode(), ruleQuantificationCrowdResult.getInfo());
            }

            // 2. 执行抽奖
            Long activityId = ruleQuantificationCrowdResult.getActivityId();
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessRequest(quantificationDrawReq.getUId(), activityId));
            if (!ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("量化人群抽奖，失败(抽奖过程异常) uId：{} treeId：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId());
                return new DrawResult(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 3. 数据转换
            DrawAwardVO drawAwardVO = drawProcessResult.getDrawAwardVO();
            AwardDto awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(activityId);

            // 4. 封装数据
            DrawResult drawRes = new DrawResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDto(awardDTO);

            logger.info("量化人群抽奖，完成 uId：{} treeId：{} drawRes：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId(), JSON.toJSONString(drawRes));

            return drawRes;
        } catch (Exception e) {
            logger.error("量化人群抽奖，失败 uId：{} treeId：{} reqJson：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId(), JSON.toJSONString(quantificationDrawReq), e);
            return new DrawResult(ResponseCode.UN_ERROR.getCode(), ResponseCode.UN_ERROR.getInfo());
        }
    }
}
