package com.simple.lottery.rpc.activity.booth;

import com.simple.lottery.rpc.activity.booth.request.DrawRequest;
import com.simple.lottery.rpc.activity.booth.request.QuantificationDrawRequest;
import com.simple.lottery.rpc.activity.booth.result.DrawResult;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: ILotteryActivityBooth
 *
 * @author: WuChengXing
 * @create: 2022-08-07 22:12
 **/
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     *
     * @param drawReq 请求参数
     * @return 抽奖结果
     */
    DrawResult doDraw(DrawRequest drawReq);

    /**
     * 量化人群抽奖
     *
     * @param quantificationDrawReq 请求参数
     * @return 抽奖结果
     */
    DrawResult doQuantificationDraw(QuantificationDrawRequest quantificationDrawReq);

}
