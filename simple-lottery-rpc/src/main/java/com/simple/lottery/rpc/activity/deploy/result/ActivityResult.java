package com.simple.lottery.rpc.activity.deploy.result;

import com.simple.lottery.common.entity.Result;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.pagination.util.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动返回值
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:00
 **/
@Data
public class ActivityResult implements Serializable {

    private static final long serialVersionUID = 4038924840792433307L;
    private Result result;
    private Page<ActivityDto> activityDTOList;

    public ActivityResult() {
    }

    public ActivityResult(Result result) {
        this.result = result;
    }

    public ActivityResult(Result result, Page<ActivityDto> activityDTOList) {
        this.result = result;
        this.activityDTOList = activityDTOList;
    }
}
