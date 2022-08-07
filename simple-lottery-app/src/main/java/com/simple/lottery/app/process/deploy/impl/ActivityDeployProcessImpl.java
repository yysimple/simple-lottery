package com.simple.lottery.app.process.deploy.impl;

import com.simple.lottery.app.process.deploy.IActivityDeployProcess;
import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.simple.pagination.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-07 16:09
 **/
@Service
public class ActivityDeployProcessImpl implements IActivityDeployProcess {

    @Resource
    private IActivityDeploy activityDeploy;

    @Override
    public Page<ActivityVO> queryActivityInfoLimitPage(ActivityInfoLimitPageRequest request) {
        return activityDeploy.queryActivityInfoLimitPage(request);
    }
}
