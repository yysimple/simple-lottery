package com.simple.lottery.domain.award.service.factory;

import com.simple.lottery.common.enums.AwardType;
import com.simple.lottery.domain.award.service.goods.IDistributionGoods;
import com.simple.lottery.domain.award.service.goods.impl.DescGoods;
import com.simple.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 各类发奖奖品配置类
 *
 * @author: WuChengXing
 * @create: 2022-07-30 12:55
 **/
public class GoodsConfig {

    /**
     * 奖品发放策略组
     */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init() {
        goodsMap.put(AwardType.DESC.getCode(), descGoods);
        goodsMap.put(AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
