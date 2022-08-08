package com.simple.lottery.domain;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.common.enums.AwardType;
import com.simple.lottery.common.enums.Ids;
import com.simple.lottery.common.enums.StrategyMode;
import com.simple.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.simple.lottery.domain.activity.model.request.ActivityConfigRequest;
import com.simple.lottery.domain.activity.model.request.PartakeRequest;
import com.simple.lottery.domain.activity.model.result.PartakeResult;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.domain.activity.model.vo.AwardVO;
import com.simple.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.simple.lottery.domain.activity.model.vo.StrategyVO;
import com.simple.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.simple.lottery.domain.activity.service.partake.IActivityPartake;
import com.simple.lottery.domain.activity.service.stateflow.IStateHandler;
import com.simple.lottery.domain.support.ids.IdGenerator;
import com.simple.lottery.infrastructure.entity.Activity;
import com.simple.lottery.infrastructure.entity.UserStrategyExport;
import com.simple.lottery.infrastructure.entity.UserTakeActivity;
import com.simple.lottery.infrastructure.mapper.ActivityMapper;
import com.simple.lottery.infrastructure.mapper.UserStrategyExportMapper;
import com.simple.lottery.infrastructure.mapper.UserTakeActivityMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    @Resource
    private IActivityDeploy activityDeploy;

    private ActivityConfigRich activityConfigRich;

    @Before
    public void init() {

        ActivityVO activity = new ActivityVO();
        activity.setActivityId(100002L);
        activity.setStrategyId(10002L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试活动描述");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(ActivityState.EDIT.getCode());
        activity.setCreator("wcx");

        StrategyVO strategy = new StrategyVO();
        strategy.setStrategyId(10002L);
        strategy.setStrategyDesc("抽奖策略");
        strategy.setStrategyMode(StrategyMode.SINGLE.getCode());
        strategy.setGrantType(1);
        strategy.setGrantDate(new Date());
        strategy.setExtInfo("");

        StrategyDetailVO strategyDetail_01 = new StrategyDetailVO();
        strategyDetail_01.setStrategyId(strategy.getStrategyId());
        strategyDetail_01.setAwardId("101");
        strategyDetail_01.setAwardName("一等奖");
        strategyDetail_01.setAwardCount(10);
        strategyDetail_01.setAwardSurplusCount(10);
        strategyDetail_01.setAwardRate(new BigDecimal("0.05"));

        StrategyDetailVO strategyDetail_02 = new StrategyDetailVO();
        strategyDetail_02.setStrategyId(strategy.getStrategyId());
        strategyDetail_02.setAwardId("102");
        strategyDetail_02.setAwardName("二等奖");
        strategyDetail_02.setAwardCount(20);
        strategyDetail_02.setAwardSurplusCount(20);
        strategyDetail_02.setAwardRate(new BigDecimal("0.15"));

        StrategyDetailVO strategyDetail_03 = new StrategyDetailVO();
        strategyDetail_03.setStrategyId(strategy.getStrategyId());
        strategyDetail_03.setAwardId("103");
        strategyDetail_03.setAwardName("三等奖");
        strategyDetail_03.setAwardCount(50);
        strategyDetail_03.setAwardSurplusCount(50);
        strategyDetail_03.setAwardRate(new BigDecimal("0.20"));

        StrategyDetailVO strategyDetail_04 = new StrategyDetailVO();
        strategyDetail_04.setStrategyId(strategy.getStrategyId());
        strategyDetail_04.setAwardId("104");
        strategyDetail_04.setAwardName("四等奖");
        strategyDetail_04.setAwardCount(100);
        strategyDetail_04.setAwardSurplusCount(100);
        strategyDetail_04.setAwardRate(new BigDecimal("0.25"));

        StrategyDetailVO strategyDetail_05 = new StrategyDetailVO();
        strategyDetail_05.setStrategyId(strategy.getStrategyId());
        strategyDetail_05.setAwardId("105");
        strategyDetail_05.setAwardName("五等奖");
        strategyDetail_05.setAwardCount(500);
        strategyDetail_05.setAwardSurplusCount(500);
        strategyDetail_05.setAwardRate(new BigDecimal("0.35"));

        List<StrategyDetailVO> strategyDetailList = new ArrayList<>();
        strategyDetailList.add(strategyDetail_01);
        strategyDetailList.add(strategyDetail_02);
        strategyDetailList.add(strategyDetail_03);
        strategyDetailList.add(strategyDetail_04);
        strategyDetailList.add(strategyDetail_05);

        strategy.setStrategyDetailList(strategyDetailList);

        AwardVO award_01 = new AwardVO();
        award_01.setAwardId("101");
        award_01.setAwardType(AwardType.DESC.getCode());
        award_01.setAwardName("电脑");
        award_01.setAwardContent("请联系活动组织者 wcx");

        AwardVO award_02 = new AwardVO();
        award_02.setAwardId("102");
        award_02.setAwardType(AwardType.DESC.getCode());
        award_02.setAwardName("手机");
        award_02.setAwardContent("请联系活动组织者 wcx");

        AwardVO award_03 = new AwardVO();
        award_03.setAwardId("103");
        award_03.setAwardType(AwardType.DESC.getCode());
        award_03.setAwardName("平板");
        award_03.setAwardContent("请联系活动组织者 wcx");

        AwardVO award_04 = new AwardVO();
        award_04.setAwardId("104");
        award_04.setAwardType(AwardType.DESC.getCode());
        award_04.setAwardName("耳机");
        award_04.setAwardContent("请联系活动组织者 wcx");

        AwardVO award_05 = new AwardVO();
        award_05.setAwardId("105");
        award_05.setAwardType(AwardType.DESC.getCode());
        award_05.setAwardName("数据线");
        award_05.setAwardContent("请联系活动组织者 wcx");

        List<AwardVO> awardList = new ArrayList<>();
        awardList.add(award_01);
        awardList.add(award_02);
        awardList.add(award_03);
        awardList.add(award_04);
        awardList.add(award_05);

        activityConfigRich = new ActivityConfigRich(activity, strategy, awardList);
    }

    @Test
    public void testCreateActivity() {
        try {
            activityDeploy.createActivity(new ActivityConfigRequest(100001L, activityConfigRich));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

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
        logger.info("提交审核，测试：{}", JSON.toJSONString(stateHandler.arraignment(100002L, ActivityState.EDIT)));
        logger.info("审核通过，测试：{}", JSON.toJSONString(stateHandler.checkPass(100002L, ActivityState.ARRAIGNMENT)));
        logger.info("运行活动，测试：{}", JSON.toJSONString(stateHandler.doing(100002L, ActivityState.PASS)));
        logger.info("二次提审，测试：{}", JSON.toJSONString(stateHandler.checkPass(100002L, ActivityState.EDIT)));
    }

    /**
     * 测试用用户领取活动表的分库分表的实现
     */
    @Test
    public void testUserTakeInsert() {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setUId("wcx144969");
        userTakeActivity.setStrategyId(10001L);
        userTakeActivity.setTakeId(121019889410L);
        userTakeActivity.setActivityId(100001L);
        userTakeActivity.setActivityName("测试活动");
        userTakeActivity.setTakeDate(new Date());
        userTakeActivity.setTakeCount(10);
        userTakeActivity.setUuid("Uhdgkw766120d");
        userTakeActivityMapper.insert(userTakeActivity);
    }

    @Test
    public void testUserStrategyExportInsert() {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId("wcx144969");
        userStrategyExport.setActivityId(idGeneratorMap.get(Ids.ShortCode).nextId());
        userStrategyExport.setOrderId(idGeneratorMap.get(Ids.SnowFlake).nextId());
        userStrategyExport.setStrategyId(idGeneratorMap.get(Ids.RandomNumeric).nextId());
        userStrategyExport.setStrategyMode(StrategyMode.SINGLE.getCode());
        userStrategyExport.setGrantType(1);
        userStrategyExport.setGrantDate(new Date());
        userStrategyExport.setGrantState(1);
        userStrategyExport.setAwardId("101");
        userStrategyExport.setAwardType(AwardType.DESC.getCode());
        userStrategyExport.setAwardName("电脑");
        userStrategyExport.setAwardContent("奖品描述");
        userStrategyExport.setUuid(String.valueOf(userStrategyExport.getOrderId()));

        userStrategyExportMapper.insert(userStrategyExport);
    }

    @Test
    public void testUserStrategyExportSelect() {
        UserStrategyExport userStrategyExport = userStrategyExportMapper.queryUserStrategyExportByUId("Uhdgkw766120d");
        logger.info("测试结果：{}", JSON.toJSONString(userStrategyExport));
    }


    @Test
    public void testActivityPartake() {
        PartakeRequest req = new PartakeRequest("wcx144969", 100002L, new Date());
        PartakeResult res = activityPartake.doPartake(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }


}
