package com.simple.lottery.domain.activity.model.result;

import com.simple.lottery.common.entity.Result;
import lombok.*;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动参与结果
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:49
 **/
@Getter
@Setter
@ToString
public class PartakeResult extends Result {

    /**
     * 策略ID
     */
    private Long strategyId;
    /**
     * 活动领取ID
     */
    private Long takeId;
    /**
     * 库存
     */
    private Integer stockCount;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;

    public PartakeResult(String code, String info) {
        super(code, info);
    }


}
