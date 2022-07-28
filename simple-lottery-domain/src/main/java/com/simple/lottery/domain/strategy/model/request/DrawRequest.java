package com.simple.lottery.domain.strategy.model.request;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:23
 **/
@Data
public class DrawRequest {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 防重ID
     */
    private String uuid;

    public DrawRequest() {
    }

    public DrawRequest(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public DrawRequest(String uId, Long strategyId, String uuid) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.uuid = uuid;
    }
}
