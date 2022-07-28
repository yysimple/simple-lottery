package com.simple.lottery.infrastructure.mapper;

import com.simple.dbrouter.annotation.DBRouter;
import com.simple.dbrouter.annotation.DbRouterStrategy;
import com.simple.lottery.infrastructure.entity.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 用户策略计算结果表Mapper
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:27
 **/
@Mapper
@DbRouterStrategy(splitTable = true)
public interface UserStrategyExportMapper {

    /**
     * 新增数据
     * @param userStrategyExport 用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     * @param uId 用户ID
     * @return 用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);

    /**
     * 更新发奖状态
     * @param userStrategyExport 发奖信息
     */
    @DBRouter
    void updateUserAwardState(UserStrategyExport userStrategyExport);

    /**
     * 更新发送MQ状态
     * @param userStrategyExport 发送消息
     */
    @DBRouter
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);

    /**
     * 扫描发货单 MQ 状态，把未发送 MQ 的单子扫描出来，做补偿
     *
     * @return 发货单
     */
    List<UserStrategyExport> scanInvoiceMqState();
}
