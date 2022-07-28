package com.simple.lottery.domain.strategy.model.vo;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品简要信息
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:17
 **/
@Data
public class AwardBriefVO {

    /** 奖品ID */
    private String awardId;

    /** 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品） */
    private Integer awardType;

    /** 奖品名称 */
    private String awardName;

    /** 奖品内容「描述、奖品码、sku」 */
    private String awardContent;
}
