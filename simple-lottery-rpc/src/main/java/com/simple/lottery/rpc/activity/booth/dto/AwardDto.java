package com.simple.lottery.rpc.activity.booth.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品dto
 *
 * @author: WuChengXing
 * @create: 2022-08-07 22:13
 **/
@Data
public class AwardDto implements Serializable {

    private static final long serialVersionUID = -6551086954398941810L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 奖品ID
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
     * 奖品内容「描述、奖品码、sku」
     */
    private String awardContent;

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
}
