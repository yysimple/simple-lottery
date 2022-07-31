package com.simple.lottery.domain.activity.model.vo;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 用户领取活动记录
 *
 * @author: WuChengXing
 * @create: 2022-07-31 15:53
 **/
@Data
public class UserTakeActivityVO {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动领取ID
     */
    private Long takeId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 活动单使用状态 0未使用、1已使用
     * Constants.TaskState
     */
    private Integer state;
}
