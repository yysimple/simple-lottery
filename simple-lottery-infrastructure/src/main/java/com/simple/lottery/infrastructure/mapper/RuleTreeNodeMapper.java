package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.infrastructure.entity.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 19:21
 **/
@Mapper
public interface RuleTreeNodeMapper {
    /**
     * 查询规则树节点
     *
     * @param treeId 规则树ID
     * @return 规则树节点集合
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点数量
     *
     * @param treeId 规则树ID
     * @return 节点数量
     */
    int queryTreeNodeCount(Long treeId);

    /**
     * 查询规则树节点
     *
     * @param treeId 规则树ID
     * @return 节点集合
     */
    List<RuleTreeNode> queryTreeRulePoint(Long treeId);
}
