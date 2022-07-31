package com.simple.lottery.domain.award.service.goods;

import com.simple.lottery.domain.award.model.request.GoodsRequest;
import com.simple.lottery.domain.award.model.result.DistributionResult;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖，抽象出配送货物接口，把各类奖品模拟成货物、配送代表着发货，包括虚拟奖品和实物奖品
 *
 * @author: WuChengXing
 * @create: 2022-07-30 12:57
 **/
public interface IDistributionGoods {

    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     *
     * @param request 物品信息
     * @return 配送结果
     */
    DistributionResult doDistribution(GoodsRequest request);
}
