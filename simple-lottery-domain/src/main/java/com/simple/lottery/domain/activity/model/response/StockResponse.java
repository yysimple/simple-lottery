package com.simple.lottery.domain.activity.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 库存处理结果
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:50
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {

    /**
     * 库存 Key
     */
    private String stockKey;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;
}
