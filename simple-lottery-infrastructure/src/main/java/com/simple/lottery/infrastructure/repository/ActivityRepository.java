package com.simple.lottery.infrastructure.repository;

import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.domain.activity.model.vo.*;
import com.simple.lottery.domain.activity.repository.IActivityRepository;
import com.simple.lottery.infrastructure.entity.Activity;
import com.simple.lottery.infrastructure.entity.Award;
import com.simple.lottery.infrastructure.entity.Strategy;
import com.simple.lottery.infrastructure.entity.StrategyDetail;
import com.simple.lottery.infrastructure.mapper.ActivityMapper;
import com.simple.lottery.infrastructure.mapper.AwardMapper;
import com.simple.lottery.infrastructure.mapper.StrategyDetailMapper;
import com.simple.lottery.infrastructure.mapper.StrategyMapper;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 20:59
 **/
public class ActivityRepository implements IActivityRepository {

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private AwardMapper awardMapper;
    @Resource
    private StrategyMapper strategyMapper;
    @Resource
    private StrategyDetailMapper strategyDetailMapper;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityMapper.insert(req);
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVO awardVO : awardList) {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO, award);
            req.add(award);
        }
        awardMapper.insertList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyMapper.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailMapper.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<ActivityState> beforeState, Enum<ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((ActivityState) beforeState).getCode(), ((ActivityState) afterState).getCode());
        int count = activityMapper.alterState(alterStateVO);
        return 1 == count;
    }
}
