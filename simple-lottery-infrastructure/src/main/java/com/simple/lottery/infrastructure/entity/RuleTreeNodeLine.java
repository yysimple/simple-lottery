package com.simple.lottery.infrastructure.entity;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则树节点连线
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:21
 **/
@Data
public class RuleTreeNodeLine {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 节点From
     */
    private Long nodeIdFrom;
    /**
     * 节点To
     */
    private Long nodeIdTo;
    /**
     * 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围]
     */
    private Integer ruleLimitType;
    /**
     * 限定值
     */
    private String ruleLimitValue;
}
