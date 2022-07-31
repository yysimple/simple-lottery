package com.simple.lottery.domain.award.model.result;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 商品配送结果
 *
 * @author: WuChengXing
 * @create: 2022-07-30 00:17
 **/
@Data
public class DistributionResult {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String info;

    /**
     * 结算单ID，如：发券后有券码、发货后有单号等，用于存根查询
     */
    private String statementId;

    public DistributionResult() {
    }

    /**
     * 构造函数
     *
     * @param uId  用户ID
     * @param code 编码
     * @param info 描述
     */
    public DistributionResult(String uId, Integer code, String info) {
        this.uId = uId;
        this.code = code;
        this.info = info;
    }

    public DistributionResult(String uId, Integer code, String info, String statementId) {
        this.uId = uId;
        this.code = code;
        this.info = info;
        this.statementId = statementId;
    }
}
