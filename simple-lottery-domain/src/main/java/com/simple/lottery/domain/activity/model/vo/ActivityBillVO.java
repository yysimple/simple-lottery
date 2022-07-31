package com.simple.lottery.domain.activity.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动账单【库存、状态、日期、个人参与次数】
 *
 * @author: WuChengXing
 * @create: 2022-07-31 15:57
 **/
@Data
public class ActivityBillVO {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * activity 活动ID
     */
    private Long activityId;
    /**
     * activity 活动名称
     */
    private String activityName;
    /**
     * activity 开始时间
     */
    private Date beginDateTime;
    /**
     * activity 结束时间
     */
    private Date endDateTime;
    /**
     * 库存
     */
    private Integer stockCount;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;
    /**
     * activity 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     * Constants.ActivityState
     */
    private Integer state;
    /**
     * activity 策略ID
     */
    private Long strategyId;
    /**
     * activity 每人可参与次数
     */
    private Integer takeCount;

    /**
     * user_take_activity_count 剩余领取次数
     */
    private Integer userTakeLeftCount;
}

