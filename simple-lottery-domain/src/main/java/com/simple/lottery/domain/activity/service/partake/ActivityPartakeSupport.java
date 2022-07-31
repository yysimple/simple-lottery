package com.simple.lottery.domain.activity.service.partake;

import com.simple.lottery.domain.activity.model.request.PartakeRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityBillVO;
import com.simple.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-31 15:56
 **/
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeRequest request) {
        return activityRepository.queryActivityBill(request);
    }
}
