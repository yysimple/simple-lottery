package com.simple.lottery.domain.rule.model.vo;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则树根配置
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:10
 **/
@Data
public class TreeRootVO {

    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 规则树根ID
     */
    private Long treeRootNodeId;
    /**
     * 规则树名称
     */
    private String treeName;
}
