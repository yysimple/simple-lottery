package com.simple.lottery.infrastructure.repository;

import com.simple.lottery.common.constant.NodeType;
import com.simple.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.simple.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.simple.lottery.domain.rule.model.vo.TreeNodeVO;
import com.simple.lottery.domain.rule.model.vo.TreeRootVO;
import com.simple.lottery.domain.rule.repository.IRuleRepository;
import com.simple.lottery.infrastructure.entity.RuleTree;
import com.simple.lottery.infrastructure.entity.RuleTreeNode;
import com.simple.lottery.infrastructure.entity.RuleTreeNodeLine;
import com.simple.lottery.infrastructure.mapper.RuleTreeMapper;
import com.simple.lottery.infrastructure.mapper.RuleTreeNodeLineMapper;
import com.simple.lottery.infrastructure.mapper.RuleTreeNodeMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则信息仓储服务
 *
 * @author: WuChengXing
 * @create: 2022-08-06 18:24
 **/
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private RuleTreeMapper ruleTreeMapper;
    @Resource
    private RuleTreeNodeMapper ruleTreeNodeMapper;
    @Resource
    private RuleTreeNodeLineMapper ruleTreeNodeLineMapper;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        // 规则树
        RuleTree ruleTree = ruleTreeMapper.queryRuleTreeByTreeId(treeId);
        TreeRootVO treeRoot = new TreeRootVO();
        treeRoot.setTreeId(ruleTree.getId());
        treeRoot.setTreeRootNodeId(ruleTree.getTreeRootNodeId());
        treeRoot.setTreeName(ruleTree.getTreeName());

        // 树节点 -> 树连接线
        Map<Long, TreeNodeVO> treeNodeMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodeList = ruleTreeNodeMapper.queryRuleTreeNodeList(treeId);
        for (RuleTreeNode treeNode : ruleTreeNodeList) {
            List<TreeNodeLineVO> treeNodeLineInfoList = new ArrayList<>();
            if (NodeType.STEM.equals(treeNode.getNodeType())) {

                RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();
                ruleTreeNodeLineReq.setTreeId(treeId);
                ruleTreeNodeLineReq.setNodeIdFrom(treeNode.getId());
                List<RuleTreeNodeLine> ruleTreeNodeLineList = ruleTreeNodeLineMapper.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                for (RuleTreeNodeLine nodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeLineInfo = new TreeNodeLineVO();
                    treeNodeLineInfo.setNodeIdFrom(nodeLine.getNodeIdFrom());
                    treeNodeLineInfo.setNodeIdTo(nodeLine.getNodeIdTo());
                    treeNodeLineInfo.setRuleLimitType(nodeLine.getRuleLimitType());
                    treeNodeLineInfo.setRuleLimitValue(nodeLine.getRuleLimitValue());
                    treeNodeLineInfoList.add(treeNodeLineInfo);
                }
            }
            TreeNodeVO treeNodeInfo = new TreeNodeVO();
            treeNodeInfo.setTreeId(treeNode.getTreeId());
            treeNodeInfo.setTreeNodeId(treeNode.getId());
            treeNodeInfo.setNodeType(treeNode.getNodeType());
            treeNodeInfo.setNodeValue(treeNode.getNodeValue());
            treeNodeInfo.setRuleKey(treeNode.getRuleKey());
            treeNodeInfo.setRuleDesc(treeNode.getRuleDesc());
            treeNodeInfo.setTreeNodeLineInfoList(treeNodeLineInfoList);

            treeNodeMap.put(treeNode.getId(), treeNodeInfo);
        }

        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRoot(treeRoot);
        treeRuleRich.setTreeNodeMap(treeNodeMap);

        return treeRuleRich;
    }
}
