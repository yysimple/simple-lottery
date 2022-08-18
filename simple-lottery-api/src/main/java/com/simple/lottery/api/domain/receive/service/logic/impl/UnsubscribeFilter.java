package com.simple.lottery.api.domain.receive.service.logic.impl;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;
import com.simple.lottery.api.domain.receive.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 取消关注微信公众号
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:06
 **/
@Service("unsubscribe")
public class UnsubscribeFilter implements LogicFilter {

    private final Logger logger = LoggerFactory.getLogger(UnsubscribeFilter.class);

    @Override
    public String filter(BehaviorMatter request) {
        logger.info("用户{}已取消关注", request.getOpenId());
        return null;
    }
}
