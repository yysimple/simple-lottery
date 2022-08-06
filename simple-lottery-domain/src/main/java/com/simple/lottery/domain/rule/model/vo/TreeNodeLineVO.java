package com.simple.lottery.domain.rule.model.vo;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则树线信息
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:11
 **/
@Data
public class TreeNodeLineVO {

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
