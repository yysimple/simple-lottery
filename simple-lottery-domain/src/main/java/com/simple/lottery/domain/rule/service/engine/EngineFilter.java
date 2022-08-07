package com.simple.lottery.domain.rule.service.engine;

import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.model.result.EngineResult;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则过滤器引擎
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:39
 **/
public interface EngineFilter {

    /**
     * 规则过滤器接口
     *
     * @param matter 规则决策物料
     * @return 规则决策结果
     */
    EngineResult process(final DecisionMatterRequest matter);
}
