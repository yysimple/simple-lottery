package com.simple.lottery.domain.rule.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:10
 **/
@Data
public class TreeNodeVO {

    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 规则树节点ID
     */
    private Long treeNodeId;
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
    /**
     * 节点链路
     */
    private List<TreeNodeLineVO> treeNodeLineInfoList;
}
