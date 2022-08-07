package com.simple.lottery.services.interfaces.rpc;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.app.process.deploy.IActivityDeployProcess;
import com.simple.lottery.common.entity.Result;
import com.simple.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.rpc.activity.deploy.ILotteryActivityDeploy;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.lottery.rpc.activity.deploy.request.ActivityPageRequest;
import com.simple.lottery.rpc.activity.deploy.result.ActivityResult;
import com.simple.lottery.services.interfaces.transfer.IMapping;
import com.simple.pagination.util.Page;
import com.simple.rpc.common.annotation.SimpleRpcReference;
import com.simple.rpc.common.annotation.SimpleRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-07 16:03
 **/
@SimpleRpcService
public class LotteryActivityDeploy implements ILotteryActivityDeploy {

    private final Logger logger = LoggerFactory.getLogger(LotteryActivityDeploy.class);

    @Resource
    private IActivityDeployProcess activityDeploy;

    @Resource
    private IMapping<ActivityVO, ActivityDto> activityMapping;

    @Override
    public ActivityResult queryActivityListByPageForErp(ActivityPageRequest req) {
        try {
            logger.info("活动部署分页数据查询开始 erpID：{}", req.getErpId());

            // 1. 包装入参
            ActivityInfoLimitPageRequest activityInfoLimitPageReq = new ActivityInfoLimitPageRequest();
            activityInfoLimitPageReq.setPageIndex(req.getPageIndex());
            activityInfoLimitPageReq.setPageSize(req.getPageSize());
            activityInfoLimitPageReq.setActivityId(req.getActivityId());
            activityInfoLimitPageReq.setActivityName(req.getActivityName());

            // 2. 查询结果
            Page<ActivityVO> activityVOPage = activityDeploy.queryActivityInfoLimitPage(activityInfoLimitPageReq);
            if (Objects.isNull(activityVOPage) || CollectionUtils.isEmpty(activityVOPage.getList())) {
                logger.warn("暂未查询到活动信息！！");
                return new ActivityResult(Result.buildErrorResult("暂未查询到活动信息!!"));
            }
            List<ActivityVO> list = activityVOPage.getList();
            // 3. 转换对象
            Page<ActivityDto> activityDtoPage = activityMapping.sourceToTargetPage(activityVOPage);

            // 4. 封装数据
            ActivityResult activityRes = new ActivityResult(Result.buildSuccessResult());
            activityRes.setActivityDTOList(activityDtoPage);

            logger.info("活动部署分页数据查询完成 erpID：{} count：{}", req.getErpId(), activityDtoPage.getTotal());

            // 5. 返回结果
            return activityRes;
        } catch (Exception e) {
            logger.error("活动部署分页数据查询失败 erpID：{} reqStr：{}", req.getErpId(), JSON.toJSON(req), e);
            return new ActivityResult(Result.buildErrorResult());
        }
    }
}
