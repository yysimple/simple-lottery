package com.simple.lottery.api.domain.receive.service.logic.impl;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;
import com.simple.lottery.api.domain.receive.service.logic.LogicFilter;
import com.simple.lottery.rpc.activity.booth.ILotteryActivityBooth;
import com.simple.lottery.rpc.activity.booth.dto.AwardDto;
import com.simple.lottery.rpc.activity.booth.request.DrawRequest;
import com.simple.lottery.rpc.activity.booth.result.DrawResult;
import com.simple.rpc.common.annotation.SimpleRpcReference;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:06
 **/
@Service
public class LotteryLogicFilter implements LogicFilter {

    @SimpleRpcReference
    private ILotteryActivityBooth lotteryActivityBooth;

    @Override
    public String filter(BehaviorMatter request) {

        DrawRequest drawReq = new DrawRequest();
        drawReq.setUId(request.getOpenId().substring(1, 11));
        drawReq.setActivityId(100001L);

        DrawResult drawRes = lotteryActivityBooth.doDraw(drawReq);
        AwardDto awardDTO = drawRes.getAwardDto();

        if (!"0000".equals(drawRes.getCode())) {
            return "抽奖💐 提示：" + drawRes.getInfo();
        }

        return "恭喜💐 您已中奖：" + awardDTO.getAwardName() + " - Lottery 抽奖系统测试";
    }
}
