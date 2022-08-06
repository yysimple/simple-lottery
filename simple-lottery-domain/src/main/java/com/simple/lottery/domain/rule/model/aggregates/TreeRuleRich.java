package com.simple.lottery.domain.rule.model.aggregates;

import com.simple.lottery.domain.rule.model.vo.TreeNodeVO;
import com.simple.lottery.domain.rule.model.vo.TreeRootVO;
import lombok.Data;

import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则树聚合
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:09
 **/
@Data
public class TreeRuleRich {

    /**
     * 树根信息
     */
    private TreeRootVO treeRoot;

    /**
     * 树节点ID -> 子节点
     */
    private Map<Long, TreeNodeVO> treeNodeMap;
}
