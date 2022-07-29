package com.simple.lottery.domain.award.repository;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品表仓储服务接口
 *
 * @author: WuChengXing
 * @create: 2022-07-30 00:38
 **/
public interface IOrderRepository {

    /**
     * 更新奖品发放状态
     *
     * @param uId               用户ID
     * @param orderId           订单ID
     * @param awardId           奖品ID
     * @param grantState        奖品状态
     */
    void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState);
}
