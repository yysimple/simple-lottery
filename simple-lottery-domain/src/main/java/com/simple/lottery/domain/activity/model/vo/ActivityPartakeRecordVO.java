package com.simple.lottery.domain.activity.model.vo;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动参与记录
 *
 * @author: WuChengXing
 * @create: 2022-07-31 15:50
 **/
@Data
public class ActivityPartakeRecordVO {

    /**
     * 用户ID
     */
    private String uId;
    /**
     * activity 活动ID
     */
    private Long activityId;
    /**
     * 库存
     */
    private Integer stockCount;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;
}
