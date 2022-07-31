package com.simple.lottery.domain.activity.service.partake.impl;

import com.simple.dbrouter.strategy.DbRouterStrategy;
import com.simple.lottery.common.entity.Result;
import com.simple.lottery.common.entity.SimpleResponse;
import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.common.enums.ResponseCode;
import com.simple.lottery.domain.activity.model.request.PartakeRequest;
import com.simple.lottery.domain.activity.model.result.StockResult;
import com.simple.lottery.domain.activity.model.vo.*;
import com.simple.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.simple.lottery.domain.activity.service.partake.BaseActivityPartake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动参与功能实现
 *
 * @author: WuChengXing
 * @create: 2022-07-31 16:41
 **/
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private final Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private DbRouterStrategy dbRouter;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeRequest partake, ActivityBillVO bill) {
        // 校验：活动状态
        if (!ActivityState.DOING.getCode().equals(bill.getState())) {
            logger.warn("活动当前状态非可用 state：{}", bill.getState());
            return Result.buildResult(ResponseCode.UN_ERROR, "活动当前状态非可用");
        }

        // 校验：活动日期
        if (bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())) {
            logger.warn("活动时间范围非可用 beginDateTime：{} endDateTime：{}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildResult(ResponseCode.UN_ERROR, "活动时间范围非可用");
        }

        // 校验：活动库存
        if (bill.getStockSurplusCount() <= 0) {
            logger.warn("活动剩余库存非可用 stockSurplusCount：{}", bill.getStockSurplusCount());
            return Result.buildResult(ResponseCode.UN_ERROR, "活动剩余库存非可用");
        }

        // 校验：个人库存 - 个人活动剩余可领取次数
        if (null != bill.getUserTakeLeftCount() && bill.getUserTakeLeftCount() <= 0) {
            logger.warn("个人领取次数非可用 userTakeLeftCount：{}", bill.getUserTakeLeftCount());
            return Result.buildResult(ResponseCode.UN_ERROR, "个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeRequest req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if (0 == count) {
            logger.error("扣减活动库存失败 activityId：{}", req.getActivityId());
            return Result.buildResult(ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {
        return activityRepository.subtractionActivityStockByRedis(uId, activityId, stockCount);
    }

    @Override
    protected void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code) {
        activityRepository.recoverActivityCacheStockByRedis(activityId, tokenKey, code);
    }

    @Override
    protected Result grabActivity(PartakeRequest partake, ActivityBillVO bill, Long takeId) {
        try {
            dbRouter.doRouter(partake.getUId());
            return transactionTemplate.execute(status -> {
                try {
                    // 扣减个人已参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getUId());
                    if (0 == updateCount) {
                        status.setRollbackOnly();
                        logger.error("领取活动，扣减个人已参与次数失败 activityId：{} uId：{}", partake.getActivityId(), partake.getUId());
                        return Result.buildResult(ResponseCode.NO_UPDATE);
                    }

                    // 写入领取活动记录
                    userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(), bill.getStrategyId(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getUId(), partake.getPartakeDate(), takeId);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("领取活动，唯一索引冲突 activityId：{} uId：{}", partake.getActivityId(), partake.getUId(), e);
                    return Result.buildResult(ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getUId());
            return transactionTemplate.execute(status -> {
                try {
                    // 锁定活动领取记录
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrder.getUId(), drawOrder.getActivityId(), drawOrder.getTakeId());
                    if (0 == lockCount) {
                        status.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖已消耗完 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getUId());
                        return Result.buildResult(ResponseCode.NO_UPDATE);
                    }

                    // 保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getUId(), e);
                    return Result.buildResult(ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }

    }

    @Override
    public void updateInvoiceMqState(String uId, Long orderId, Integer mqState) {
        userTakeActivityRepository.updateInvoiceMqState(uId, orderId, mqState);
    }

    @Override
    public List<InvoiceVO> scanInvoiceMqState(int dbCount, int tbCount) {
        try {
            // 设置路由
            dbRouter.setDbKey(dbCount);
            dbRouter.setTableKey(tbCount);

            // 查询数据
            return userTakeActivityRepository.scanInvoiceMqState();
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public void updateActivityStock(ActivityPartakeRecordVO activityPartakeRecordVO) {
        userTakeActivityRepository.updateActivityStock(activityPartakeRecordVO);
    }
}
