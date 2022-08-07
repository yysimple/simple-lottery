package com.simple.lottery.app.process.draw;

import com.simple.lottery.app.process.draw.request.DrawProcessRequest;
import com.simple.lottery.app.process.draw.result.DrawProcessResult;
import com.simple.lottery.app.process.draw.result.RuleQuantificationCrowdResult;
import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动抽奖流程编排接口
 *
 * @author: WuChengXing
 * @create: 2022-08-02 18:26
 **/
public interface IActivityDrawProcess {

    /**
     * 执行抽奖流程
     *
     * @param request 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessRequest request);

    /**
     * 规则量化人群，返回可参与的活动ID
     *
     * @param req 规则请求
     * @return 量化结果，用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterRequest req);
}
