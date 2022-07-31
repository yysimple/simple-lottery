package com.simple.lottery.domain.strategy.service.draw;

import com.simple.lottery.domain.strategy.model.request.DrawRequest;
import com.simple.lottery.domain.strategy.model.result.DrawResult;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:36
 **/
public interface IDrawExec {


    /**
     * 抽奖方法
     * @param req 抽奖参数；用户ID、策略ID
     * @return    中奖结果
     */
    DrawResult doDrawExec(DrawRequest req);
}
