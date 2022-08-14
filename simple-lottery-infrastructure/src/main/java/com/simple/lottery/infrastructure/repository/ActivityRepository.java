package com.simple.lottery.infrastructure.repository;

import com.simple.lottery.common.constant.RedisKeyConstant;
import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.common.enums.ResponseCode;
import com.simple.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.request.PartakeRequest;
import com.simple.lottery.domain.activity.model.result.StockResult;
import com.simple.lottery.domain.activity.model.vo.*;
import com.simple.lottery.domain.activity.repository.IActivityRepository;
import com.simple.lottery.infrastructure.entity.*;
import com.simple.lottery.infrastructure.mapper.*;
import com.simple.lottery.infrastructure.util.RedisUtil;
import com.simple.pagination.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-28 20:59
 **/
@Repository
public class ActivityRepository implements IActivityRepository {

    private final Logger logger = LoggerFactory.getLogger(ActivityRepository.class);

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private AwardMapper awardMapper;

    @Resource
    private StrategyMapper strategyMapper;

    @Resource
    private StrategyDetailMapper strategyDetailMapper;

    @Resource
    private UserTakeActivityCountMapper userTakeActivityCountMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void addActivity(ActivityVO activityVO) {
        Activity req = new Activity();
        activityMapper.insert(Activity.vo2Entity(activityVO));
        // 设置活动库存 KEY
        redisUtil.set(RedisKeyConstant.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityVO.getActivityId()), 0);
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVO awardVO : awardList) {
            Award award = new Award();
            award.setAwardId(awardVO.getAwardId());
            award.setAwardType(awardVO.getAwardType());
            award.setAwardName(awardVO.getAwardName());
            award.setAwardContent(awardVO.getAwardContent());
            req.add(award);
        }
        awardMapper.insertList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        req.setStrategyId(strategy.getStrategyId());
        req.setStrategyDesc(strategy.getStrategyDesc());
        req.setStrategyMode(strategy.getStrategyMode());
        req.setGrantType(strategy.getGrantType());
        req.setGrantDate(strategy.getGrantDate());
        req.setExtInfo(strategy.getExtInfo());

        strategyMapper.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            strategyDetail.setStrategyId(strategyDetailVO.getStrategyId());
            strategyDetail.setAwardId(strategyDetailVO.getAwardId());
            strategyDetail.setAwardName(strategyDetailVO.getAwardName());
            strategyDetail.setAwardCount(strategyDetailVO.getAwardCount());
            strategyDetail.setAwardSurplusCount(strategyDetailVO.getAwardSurplusCount());
            strategyDetail.setAwardRate(strategyDetailVO.getAwardRate());
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

    @Override
    public ActivityBillVO queryActivityBill(PartakeRequest req) {

        // 查询活动信息
        Activity activity = activityMapper.queryActivityById(req.getActivityId());

        // 从缓存中获取库存
        Object usedStockCountObj = redisUtil.get(RedisKeyConstant.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(req.getActivityId()));

        // 查询领取次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setUId(req.getUId());
        userTakeActivityCountReq.setActivityId(req.getActivityId());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountMapper.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 封装结果信息
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setUId(req.getUId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setStockCount(activity.getStockCount());
        activityBillVO.setStockSurplusCount(Objects.isNull(usedStockCountObj) ? activity.getStockSurplusCount() : activity.getStockCount() - Integer.parseInt(String.valueOf(usedStockCountObj)));
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setState(activity.getState());
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null : userTakeActivityCount.getLeftCount());

        return activityBillVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityMapper.subtractionActivityStock(activityId);
    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        List<Activity> activityList = activityMapper.scanToDoActivityList(id);
        List<ActivityVO> activityVOList = new ArrayList<>(activityList.size());
        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setBeginDateTime(activity.getBeginDateTime());
            activityVO.setEndDateTime(activity.getEndDateTime());
            activityVO.setState(activity.getState());
            activityVOList.add(activityVO);
        }
        return activityVOList;
    }

    @Override
    public StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {

        //  1. 获取抽奖活动库存 Key
        String stockKey = RedisKeyConstant.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityId);

        // 2. 扣减库存，目前占用库存数
        Integer stockUsedCount = (int) redisUtil.incr(stockKey, 1);

        // 3. 超出库存判断，进行恢复原始库存
        if (stockUsedCount > stockCount) {
            redisUtil.decr(stockKey, 1);
            return new StockResult(ResponseCode.OUT_OF_STOCK.getCode(), ResponseCode.OUT_OF_STOCK.getInfo());
        }

        // 4. 以活动库存占用编号，生成对应加锁Key，细化锁的颗粒度
        String stockTokenKey = RedisKeyConstant.KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(activityId, stockUsedCount);

        // 5. 使用 Redis.setNx 加一个分布式锁
        boolean lockToken = redisUtil.setNx(stockTokenKey, 350L);
        if (!lockToken) {
            logger.info("抽奖活动{}用户秒杀{}扣减库存，分布式锁失败：{}", activityId, uId, stockTokenKey);
            return new StockResult(ResponseCode.ERR_TOKEN.getCode(), ResponseCode.ERR_TOKEN.getInfo());
        }

        return new StockResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), stockTokenKey, stockCount - stockUsedCount);
    }

    @Override
    public void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code) {
        // 删除分布式锁 Key
        redisUtil.del(tokenKey);
    }

    @Override
    public Page<ActivityVO> queryActivityInfoLimitPage(ActivityInfoLimitPageRequest req) {
        List<ActivityVO> list = activityMapper.queryActivityInfoList(req);
        return Page.of(list, req.getPageIndex(), req.getPageSize());
    }
}
