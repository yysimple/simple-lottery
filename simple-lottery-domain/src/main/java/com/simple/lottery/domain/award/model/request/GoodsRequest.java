package com.simple.lottery.domain.award.model.request;

import com.simple.lottery.domain.award.model.vo.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品发货信息
 *
 * @author: WuChengXing
 * @create: 2022-07-30 00:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRequest {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 抽奖单号 ID
     */
    private Long orderId;

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品内容「描述、奖品码、sku」
     */
    private String awardContent;

    /**
     * 四级送货地址（只有实物类商品需要地址）
     */
    private ShippingAddress shippingAddress;

    /**
     * 扩展信息，用于一些个性商品发放所需要的透传字段内容
     */
    private String extInfo;

    public GoodsRequest(String uId, Long orderId, String awardId, String awardName, String awardContent) {
        this.uId = uId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }

    public GoodsRequest(String uId, Long orderId, String awardId, String awardName, String awardContent, ShippingAddress shippingAddress) {
        this.uId = uId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.shippingAddress = shippingAddress;
    }
}
