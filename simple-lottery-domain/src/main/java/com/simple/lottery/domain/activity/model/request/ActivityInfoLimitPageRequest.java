package com.simple.lottery.domain.activity.model.request;

import com.simple.lottery.common.entity.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-31 16:00
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityInfoLimitPageRequest extends PageRequest {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;
}
