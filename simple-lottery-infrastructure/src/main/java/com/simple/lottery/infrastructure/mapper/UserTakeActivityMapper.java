package com.simple.lottery.infrastructure.mapper;

import com.simple.dbrouter.annotation.DBRouter;
import com.simple.lottery.infrastructure.entity.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 用户领取活动表Mapper
 *
 * @author: WuChengXing
 * @create: 2022-07-28 19:18
 **/
@Mapper
public interface UserTakeActivityMapper {

    /**
     * 插入用户领取活动信息
     *
     * @param userTakeActivity 入参
     */
    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 锁定活动领取记录
     *
     * @param userTakeActivity 入参
     * @return 更新结果
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * 查询此活动ID，用户最早领取但未消费的一条记录【这部分一般会有业务流程限制，比如是否处理最先还是最新领取单，要根据自己的业务实际场景进行处理】
     *
     * @param userTakeActivity 请求入参
     * @return 领取结果
     */
    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);
}
