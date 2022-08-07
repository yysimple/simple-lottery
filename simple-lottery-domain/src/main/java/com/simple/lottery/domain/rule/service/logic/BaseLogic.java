package com.simple.lottery.domain.rule.service.logic;

import com.simple.lottery.common.constant.GlobalConstant;
import com.simple.lottery.common.constant.RuleLimitType;
import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则基础抽象类
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:31
 **/
public abstract class BaseLogic implements LogicFilter {

    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        for (TreeNodeLineVO nodeLine : treeNodeLineInfoList) {
            if (decisionLogic(matterValue, nodeLine)) {
                return nodeLine.getNodeIdTo();
            }
        }
        return GlobalConstant.TREE_NULL_NODE;
    }

    /**
     * 获取规则比对值
     *
     * @param decisionMatter 决策物料
     * @return 比对值
     */
    @Override
    public abstract String matterValue(DecisionMatterRequest decisionMatter);

    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }
}
