package com.simple.lottery.domain.activity.service.stateflow;

import com.simple.lottery.common.entity.Result;
import com.simple.lottery.common.enums.ActivityState;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 状态处理接口
 *
 * @author: WuChengXing
 * @create: 2022-07-31 23:06
 **/
public interface IStateHandler {

    /**
     * 提审
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result arraignment(Long activityId, Enum<ActivityState> currentStatus);

    /**
     * 审核通过
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result checkPass(Long activityId, Enum<ActivityState> currentStatus);

    /**
     * 审核拒绝
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result checkRefuse(Long activityId, Enum<ActivityState> currentStatus);

    /**
     * 撤销审核
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result checkRevoke(Long activityId, Enum<ActivityState> currentStatus);

    /**
     * 关闭
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result close(Long activityId, Enum<ActivityState> currentStatus);

    /**
     * 开启
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result open(Long activityId, Enum<ActivityState> currentStatus);

    /**
     * 运行活动中
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 审核结果
     */
    Result doing(Long activityId, Enum<ActivityState> currentStatus);

}
