package com.simple.lottery.domain.activity.model.request;

import lombok.Data;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 参与活动请求
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:46
 **/
@Data
public class PartakeRequest {

    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动领取时间
     */
    private Date partakeDate;

    public PartakeRequest() {
    }

    public PartakeRequest(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();
    }

    public PartakeRequest(String uId, Long activityId, Date partakeDate) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
    }
}
