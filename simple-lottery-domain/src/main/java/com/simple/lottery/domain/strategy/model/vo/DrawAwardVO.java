package com.simple.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 中奖奖品信息
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawAwardVO {

    /**
     * 用户ID
     */
    private String uId;

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


    public DrawAwardVO(String uId, String awardId, Integer awardType, String awardName, String awardContent) {
        this.uId = uId;
        this.awardId = awardId;
        this.awardType = awardType;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }
}
