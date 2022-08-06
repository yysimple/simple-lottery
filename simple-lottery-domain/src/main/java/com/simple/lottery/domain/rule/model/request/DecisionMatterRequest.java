package com.simple.lottery.domain.rule.model.request;

import lombok.Data;

import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 决策物料
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:13
 **/
@Data
public class DecisionMatterRequest {

    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 决策值
     */
    private Map<String, Object> valMap;

    public DecisionMatterRequest() {
    }

    public DecisionMatterRequest(String userId, Long treeId, Map<String, Object> valMap) {
        this.userId = userId;
        this.treeId = treeId;
        this.valMap = valMap;
    }
}
