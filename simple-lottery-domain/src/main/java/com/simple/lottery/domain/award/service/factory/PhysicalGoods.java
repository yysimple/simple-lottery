package com.simple.lottery.domain.award.service.factory;

import com.simple.lottery.common.enums.AwardState;
import com.simple.lottery.common.enums.GrantState;
import com.simple.lottery.domain.award.model.request.GoodsRequest;
import com.simple.lottery.domain.award.model.result.DistributionResult;
import com.simple.lottery.domain.award.service.goods.DistributionBase;
import com.simple.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-30 13:18
 **/
@Component
public class PhysicalGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionResult doDistribution(GoodsRequest request) {

        // 模拟调用实物发奖
        logger.info("模拟调用实物发奖 uId：{} awardContent：{}", request.getUId(), request.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(request.getUId(), request.getOrderId(), request.getAwardId(), GrantState.COMPLETE.getCode());

        return new DistributionResult(request.getUId(), AwardState.SUCCESS.getCode(), AwardState.SUCCESS.getInfo());
    }
}
