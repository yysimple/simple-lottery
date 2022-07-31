package com.simple.lottery.domain;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.enums.Ids;
import com.simple.lottery.common.enums.StrategyMode;
import com.simple.lottery.domain.strategy.model.request.DrawRequest;
import com.simple.lottery.domain.strategy.model.result.DrawResult;
import com.simple.lottery.domain.strategy.model.vo.AwardRateVO;
import com.simple.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.simple.lottery.domain.strategy.service.draw.IDrawExec;
import com.simple.lottery.domain.support.ids.IdGenerator;
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
import java.util.List;
import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖类测试
 *
 * @author: WuChengXing
 * @create: 2022-07-29 13:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {
    private final Logger logger = LoggerFactory.getLogger(DrawAlgorithmTest.class);

    @Resource(name = "entiretyRateRandomDrawAlgorithm")
    //@Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

    @Resource(name = "drawExec")
    private IDrawExec iDrawExec;

    @Before
    public void init() {
        // 奖品信息
        List<AwardRateVO> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateVO("一等奖：IMac", new BigDecimal("0.05")));
        strategyList.add(new AwardRateVO("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateVO("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateVO("四等奖：AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateVO("五等奖：充电宝", new BigDecimal("0.35")));

        // 初始数据
        randomDrawAlgorithm.initRateTuple(10001L, StrategyMode.SINGLE.getCode(), strategyList);
    }

    @Test
    public void testRandomDrawAlgorithm() {

        List<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖：AirPods");

        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + randomDrawAlgorithm.randomDraw(10001L, excludeAwardIds));
        }

    }

    @Test
    public void testDrawExec() {
        DrawResult drawResult = iDrawExec.doDrawExec(new DrawRequest("wcx001", 10001L));
        logger.info("测试结果：{}", JSON.toJSONString(drawResult));
    }

    @Resource
    private Map<Ids, IdGenerator> idGeneratorMap;

    @Test
    public void test_ids() {
        logger.info("雪花算法策略，生成ID：{}", idGeneratorMap.get(Ids.SnowFlake).nextId());
        logger.info("日期算法策略，生成ID：{}", idGeneratorMap.get(Ids.ShortCode).nextId());
        logger.info("随机算法策略，生成ID：{}", idGeneratorMap.get(Ids.RandomNumeric).nextId());
    }
}
