package com.simple.lottery.api.domain.receive.service.logic;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 逻辑接口
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:01
 **/
public interface LogicFilter {

    String filter(BehaviorMatter request);
}
