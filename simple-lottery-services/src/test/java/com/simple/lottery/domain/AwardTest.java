package com.simple.lottery.domain;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.common.enums.DrawState;
import com.simple.lottery.domain.award.model.request.GoodsRequest;
import com.simple.lottery.domain.award.model.result.DistributionResult;
import com.simple.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.simple.lottery.domain.award.service.goods.IDistributionGoods;
import com.simple.lottery.domain.strategy.model.request.DrawRequest;
import com.simple.lottery.domain.strategy.model.result.DrawResult;
import com.simple.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.simple.lottery.domain.strategy.service.draw.IDrawExec;
import com.simple.lottery.infrastructure.mapper.ActivityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 奖品相关的测试类
 *
 * @author: WuChengXing
 * @create: 2022-07-30 15:59
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardTest {

    private Logger logger = LoggerFactory.getLogger(AwardTest.class);

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @Test
    public void testDrawExec() {
        drawExec.doDrawExec(new DrawRequest("wcx", 10001L));
        drawExec.doDrawExec(new DrawRequest("zyy", 10001L));
        drawExec.doDrawExec(new DrawRequest("ysq", 10001L));
        drawExec.doDrawExec(new DrawRequest("cc", 10001L));
    }


    @Test
    public void testAward() {
        // 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawRequest("wcx", 10001L));

        // 判断抽奖结果
        Integer drawState = drawResult.getDrawState();
        if (DrawState.FAIL.getCode().equals(drawState)) {
            logger.info("未中奖 DrawAwardInfo is null");
            return;
        }

        // 封装发奖参数，orderId：2109313442431 为模拟ID，需要在用户参与领奖活动时生成
        DrawAwardVO drawAwardVO = drawResult.getDrawAwardInfo();
        GoodsRequest goodsReq = new GoodsRequest(drawResult.getUId(), 20220730001L, drawAwardVO.getAwardId(), drawAwardVO.getAwardName(), drawAwardVO.getAwardContent());

        // 根据 awardType 从抽奖工厂中获取对应的发奖服务
        IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(drawAwardVO.getAwardType());
        DistributionResult distributionResult = distributionGoodsService.doDistribution(goodsReq);

        logger.info("测试结果：{}", JSON.toJSONString(distributionResult));
    }

}
