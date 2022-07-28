package com.simple.lottery.infrastructure.mapper;

import com.simple.dbrouter.annotation.DBRouter;
import com.simple.lottery.infrastructure.entity.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 用户活动参与次数表
 *
 * @author: WuChengXing
 * @create: 2022-07-28 19:15
 **/
@Mapper
public interface UserTakeActivityCountMapper {

    /**
     * 查询用户领取次数信息
     *
     * @param userTakeActivityCountReq 请求入参【活动号、用户ID】
     * @return 领取结果
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    /**
     * 插入领取次数信息
     *
     * @param userTakeActivityCount 请求入参
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * 更新领取次数信息
     *
     * @param userTakeActivityCount 请求入参
     * @return 更新数量
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
