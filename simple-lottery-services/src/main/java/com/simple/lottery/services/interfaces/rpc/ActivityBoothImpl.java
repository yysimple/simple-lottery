package com.simple.lottery.services.interfaces.rpc;

import com.simple.lottery.infrastructure.entity.Activity;
import com.simple.lottery.infrastructure.mapper.ActivityMapper;
import com.simple.lottery.rpc.activity.deploy.IActivityBooth;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.lottery.rpc.activity.deploy.request.ActivityRequest;
import com.simple.rpc.common.annotation.SimpleRpcService;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:13
 **/
@SimpleRpcService
public class ActivityBoothImpl implements IActivityBooth {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public ActivityDto queryActivityById(ActivityRequest request) {
        Activity activity = activityMapper.queryActivityById(request.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return activityDto;
    }
}
