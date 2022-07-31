package com.simple.lottery.domain.activity.service.stateflow.event;

import com.simple.lottery.common.entity.Result;
import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.common.enums.ResponseCode;
import com.simple.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-31 23:54
 **/
@Service
public class CloseState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动关闭不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动关闭不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动关闭不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动关闭不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动关闭不可重复关闭");
    }

    @Override
    public Result open(Long activityId, Enum<ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, ActivityState.OPEN);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS, "活动开启完成") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result doing(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动关闭不可变更活动中");
    }
}
