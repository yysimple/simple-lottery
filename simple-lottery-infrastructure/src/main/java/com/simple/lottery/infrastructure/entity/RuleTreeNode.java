package com.simple.lottery.infrastructure.entity;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则树节点
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:21
 **/
@Data
public class RuleTreeNode {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 节点类型；1子叶、2果实
     */
    private Integer nodeType;
    /**
     * 节点值[nodeType=2]；果实值
     */
    private String nodeValue;
    /**
     * 规则Key
     */
    private String ruleKey;
    /**
     * 规则描述
     */
    private String ruleDesc;
}
