package com.simple.lottery.infrastructure.entity;

import lombok.Data;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 用户策略计算表
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:18
 **/
@Data
public class UserStrategyExport {
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
     * 订单ID
     */
    private Long orderId;
    /**
     * 策略ID
     */
    private Long strategyId;
    /**
     * 策略方式（1:单项概率、2:总体概率）
     */
    private Integer strategyMode;
    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    private Integer grantType;
    /**
     * 发奖时间
     */
    private Date grantDate;
    /**
     * 发奖状态
     */
    private Integer grantState;
    /**
     * 发奖ID
     */
    private String awardId;
    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    private Integer awardType;
    /**
     * 奖品名称
     */
    private String awardName;
    /**
     * 奖品内容「文字描述、Key、码」
     */
    private String awardContent;
    /**
     * 防重ID
     */
    private String uuid;
    /**
     * 消息发送状态（0未发送、1发送成功、2发送失败）
     */
    private Integer mqState;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
