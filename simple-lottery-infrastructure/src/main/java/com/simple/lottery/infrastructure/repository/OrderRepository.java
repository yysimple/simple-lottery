package com.simple.lottery.infrastructure.repository;

import com.simple.lottery.domain.award.repository.IOrderRepository;
import com.simple.lottery.infrastructure.entity.UserStrategyExport;
import com.simple.lottery.infrastructure.mapper.UserStrategyExportMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品表仓储服务
 *
 * @author: WuChengXing
 * @create: 2022-07-30 01:04
 **/
@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    private UserStrategyExportMapper userStrategyExportMapper;

    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportMapper.updateUserAwardState(userStrategyExport);
    }
}
