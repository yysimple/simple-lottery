package com.simple.lottery.domain.rule.service.logic.impl;

import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 年龄规则
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:36
 **/
@Service
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterRequest decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }
}
