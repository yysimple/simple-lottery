package com.simple.lottery.api.domain.receive.service.logic.impl;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;
import com.simple.lottery.api.domain.receive.service.logic.LogicFilter;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:06
 **/
@Service("subscribe")
public class SubscribeFilter implements LogicFilter {

    @Override
    public String filter(BehaviorMatter request) {
        return "感谢关注，快来回复抽奖，参与小傅哥的活动吧！";
    }
}
