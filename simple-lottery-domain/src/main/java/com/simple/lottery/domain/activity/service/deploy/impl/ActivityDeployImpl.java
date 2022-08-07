package com.simple.lottery.domain.activity.service.deploy.impl;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.simple.lottery.domain.activity.model.request.ActivityConfigRequest;
import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.domain.activity.model.vo.AwardVO;
import com.simple.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.simple.lottery.domain.activity.model.vo.StrategyVO;
import com.simple.lottery.domain.activity.repository.IActivityRepository;
import com.simple.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.simple.pagination.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 部署活动配置服务
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:35
 **/
@Service
public class ActivityDeployImpl implements IActivityDeploy {

    private final Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createActivity(ActivityConfigRequest req) throws Exception {
        logger.info("创建活动配置开始，activityId：{}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        // 添加活动配置
        ActivityVO activity = activityConfigRich.getActivity();
        activityRepository.addActivity(activity);

        // 添加奖品配置
        List<AwardVO> awardList = activityConfigRich.getAwardList();
        activityRepository.addAward(awardList);

        // 添加策略配置
        StrategyVO strategy = activityConfigRich.getStrategy();
        activityRepository.addStrategy(strategy);

        // 添加策略明细配置
        List<StrategyDetailVO> strategyDetailList = activityConfigRich.getStrategy().getStrategyDetailList();
        activityRepository.addStrategyDetailList(strategyDetailList);

        logger.info("创建活动配置完成，activityId：{}", req.getActivityId());
    }

    @Override
    public void updateActivity(ActivityConfigRequest req) {

    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        return activityRepository.scanToDoActivityList(id);
    }

    @Override
    public Page<ActivityVO> queryActivityInfoLimitPage(ActivityInfoLimitPageRequest request) {
        return activityRepository.queryActivityInfoLimitPage(request);
    }
}
