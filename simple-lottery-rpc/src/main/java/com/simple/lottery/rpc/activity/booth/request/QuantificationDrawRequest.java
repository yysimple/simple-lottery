package com.simple.lottery.rpc.activity.booth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 量化人群抽奖请求参数
 *
 * @author: WuChengXing
 * @create: 2022-08-07 22:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantificationDrawRequest {

    /**
     * 用户ID
     */
    private String uId;
    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 决策值
     */
    private Map<String, Object> valMap;
}
