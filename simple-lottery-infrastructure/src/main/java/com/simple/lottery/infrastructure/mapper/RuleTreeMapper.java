package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.infrastructure.entity.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 19:20
 **/
@Mapper
public interface RuleTreeMapper {

    /**
     * 规则树查询
     *
     * @param id ID
     * @return 规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     *
     * @param treeId 规则树ID
     * @return 规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);
}
