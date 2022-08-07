package com.simple.lottery.domain.rule.service.logic.impl;

import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 性别规则
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:37
 **/
@Service
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterRequest decisionMatter) {
        return decisionMatter.getValMap().get("gender").toString();
    }
}
