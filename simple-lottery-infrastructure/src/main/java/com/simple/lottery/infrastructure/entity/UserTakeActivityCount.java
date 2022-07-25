package com.simple.lottery.infrastructure.entity;

import lombok.Data;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 用户参与活动次数表
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:20
 **/
@Data
public class UserTakeActivityCount {

    /**
     * 自增ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 总计可领次数
     */
    private Integer totalCount;
    /**
     * 剩余领取次数
     */
    private Integer leftCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
