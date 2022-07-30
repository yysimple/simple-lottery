package com.simple.lottery.domain.award.service.goods;

import com.simple.lottery.domain.award.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-30 12:58
 **/
public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IOrderRepository awardRepository;

    protected void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        awardRepository.updateUserAwardState(uId, orderId, awardId, grantState);
    }
}
