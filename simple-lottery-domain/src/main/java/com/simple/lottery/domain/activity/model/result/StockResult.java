package com.simple.lottery.domain.activity.model.result;

import com.simple.lottery.common.entity.Result;
import lombok.*;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 库存处理结果
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:50
 **/
@Getter
@Setter
@ToString
public class StockResult extends Result {

    /**
     * 库存 Key
     */
    private String stockKey;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;

    public StockResult(String code, String info) {
        super(code, info);
    }

    public StockResult(String code, String info, String stockKey, Integer stockSurplusCount) {
        super(code, info);
        this.stockKey = stockKey;
        this.stockSurplusCount = stockSurplusCount;
    }
}
