package com.simple.lottery.domain.rule.repository;

import com.simple.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则仓库
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:19
 **/
public interface IRuleRepository {

    /**
     * 查询规则决策树配置
     *
     * @param treeId    决策树ID
     * @return          决策树配置
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
