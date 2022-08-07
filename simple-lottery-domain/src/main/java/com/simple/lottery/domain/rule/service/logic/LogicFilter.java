package com.simple.lottery.domain.rule.service.logic;

import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则过滤器接口
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:28
 **/
public interface LogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     *
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    String matterValue(DecisionMatterRequest decisionMatter);
}
