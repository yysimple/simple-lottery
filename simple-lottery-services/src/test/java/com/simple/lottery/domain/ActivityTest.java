package com.simple.lottery.domain;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.common.enums.AwardType;
import com.simple.lottery.common.enums.Ids;
import com.simple.lottery.common.enums.StrategyMode;
import com.simple.lottery.domain.activity.model.request.PartakeRequest;
import com.simple.lottery.domain.activity.model.result.PartakeResult;
import com.simple.lottery.domain.activity.service.partake.IActivityPartake;
import com.simple.lottery.domain.activity.service.stateflow.IStateHandler;
import com.simple.lottery.domain.support.ids.IdGenerator;
import com.simple.lottery.infrastructure.entity.Activity;
import com.simple.lottery.infrastructure.entity.UserStrategyExport;
import com.simple.lottery.infrastructure.entity.UserTakeActivity;
import com.simple.lottery.infrastructure.mapper.ActivityMapper;
import com.simple.lottery.infrastructure.mapper.UserStrategyExportMapper;
import com.simple.lottery.infrastructure.mapper.UserTakeActivityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 18:12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTest {

    private final Logger logger = LoggerFactory.getLogger(ActivityTest.class);

    @Resource
    ActivityMapper activityMapper;

    @Resource
    UserTakeActivityMapper userTakeActivityMapper;

    @Resource
    private IStateHandler stateHandler;
    
    @Resource
    private UserStrategyExportMapper userStrategyExportMapper;
    
    @Resource
    private Map<Ids, IdGenerator> idGeneratorMap;

    @Resource
    private IActivityPartake activityPartake;
    

    @Test
    public void testInsert() {
        Activity activity = new Activity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("wcx");
        activityMapper.insert(activity);
    }

    @Test
    public void testSelect() {
        Activity activity = activityMapper.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }

    @Test
    public void testAlterState() {
        logger.info("提交审核，测试：{}", JSON.toJSONString(stateHandler.arraignment(100001L, ActivityState.EDIT)));
        logger.info("审核通过，测试：{}", JSON.toJSONString(stateHandler.checkPass(100001L, ActivityState.ARRAIGNMENT)));
        logger.info("运行活动，测试：{}", JSON.toJSONString(stateHandler.doing(100001L, ActivityState.PASS)));
        logger.info("二次提审，测试：{}", JSON.toJSONString(stateHandler.checkPass(100001L, ActivityState.EDIT)));
    }

    /**
     * 测试用用户领取活动表的分库分表的实现
     */
    @Test
    public void testUserTakeInsert() {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setUId("Uhdgkw766120d");
        userTakeActivity.setTakeId(121019889410L);
        userTakeActivity.setActivityId(100001L);
        userTakeActivity.setActivityName("测试活动");
        userTakeActivity.setTakeDate(new Date());
        userTakeActivity.setTakeCount(10);
        userTakeActivity.setUuid("Uhdgkw766120d");
        userTakeActivityMapper.insert(userTakeActivity);
    }

    @Test
    public void test_insert() {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId("Uhdgkw766120d");
        userStrategyExport.setActivityId(idGeneratorMap.get(Ids.ShortCode).nextId());
        userStrategyExport.setOrderId(idGeneratorMap.get(Ids.SnowFlake).nextId());
        userStrategyExport.setStrategyId(idGeneratorMap.get(Ids.RandomNumeric).nextId());
        userStrategyExport.setStrategyMode(StrategyMode.SINGLE.getCode());
        userStrategyExport.setGrantType(1);
        userStrategyExport.setGrantDate(new Date());
        userStrategyExport.setGrantState(1);
        userStrategyExport.setAwardId("1");
        userStrategyExport.setAwardType(AwardType.DESC.getCode());
        userStrategyExport.setAwardName("IMac");
        userStrategyExport.setAwardContent("奖品描述");
        userStrategyExport.setUuid(String.valueOf(userStrategyExport.getOrderId()));

        userStrategyExportMapper.insert(userStrategyExport);
    }

    @Test
    public void test_select() {
        UserStrategyExport userStrategyExport = userStrategyExportMapper.queryUserStrategyExportByUId("Uhdgkw766120d");
        logger.info("测试结果：{}", JSON.toJSONString(userStrategyExport));
    }


    @Test
    public void test_activityPartake() {
        PartakeRequest req = new PartakeRequest("Uhdgkw766120d", 100001L);
        PartakeResult res = activityPartake.doPartake(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }


}
