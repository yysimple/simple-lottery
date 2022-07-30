package com.simple.lottery.domain.award.service.goods.impl;

import com.simple.lottery.common.enums.AwardState;
import com.simple.lottery.common.enums.GrantState;
import com.simple.lottery.domain.award.model.request.GoodsRequest;
import com.simple.lottery.domain.award.model.response.DistributionResponse;
import com.simple.lottery.domain.award.service.goods.DistributionBase;
import com.simple.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 描述类商品，以文字形式展示给用户
 *
 * @author: WuChengXing
 * @create: 2022-07-30 13:01
 **/
@Component
public class DescGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionResponse doDistribution(GoodsRequest request) {

        super.updateUserAwardState(request.getUId(), request.getOrderId(), request.getAwardId(), GrantState.COMPLETE.getCode());

        return new DistributionResponse(request.getUId(), AwardState.SUCCESS.getCode(), AwardState.SUCCESS.getInfo());
    }
}
