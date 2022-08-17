package com.simple.lottery.erp.domain.service;

import com.simple.lottery.common.enums.ResponseCode;
import com.simple.lottery.erp.app.IActivityService;
import com.simple.lottery.erp.domain.model.ActivityListPageRequest;
import com.simple.lottery.rpc.activity.deploy.ILotteryActivityDeploy;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.lottery.rpc.activity.deploy.request.ActivityPageRequest;
import com.simple.lottery.rpc.activity.deploy.result.ActivityResult;
import com.simple.pagination.util.Page;
import com.simple.rpc.common.annotation.SimpleRpcReference;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-17 10:52
 **/
@Service
public class ActivityServiceImpl implements IActivityService {

    @SimpleRpcReference
    private ILotteryActivityDeploy iLotteryActivityDeploy;

    @Override
    public Page<ActivityDto> queryActivityListPage(ActivityListPageRequest req) {
        // 1. 组装参数
        ActivityPageRequest activityPageReq = new ActivityPageRequest();
        activityPageReq.setErpId(req.getErpId());
        activityPageReq.setActivityId(req.getActivityId());
        activityPageReq.setActivityName(req.getActivityName());
        activityPageReq.setPageIndex(req.getPageIndex());
        activityPageReq.setPageSize(req.getPageSize());
        // 2. 查询数据
        ActivityResult activityRes = iLotteryActivityDeploy.queryActivityListByPageForErp(activityPageReq);

        // 3. 封装结果
        if (ResponseCode.SUCCESS.getCode().equals(activityRes.getResult().getCode())) {
            return activityRes.getActivityDTOList();
        } else {
            return new Page<>();
        }
    }
}
