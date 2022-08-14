package com.simple.lottery.rpc;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.rpc.activity.booth.ILotteryActivityBooth;
import com.simple.lottery.rpc.activity.booth.request.DrawRequest;
import com.simple.lottery.rpc.activity.booth.request.QuantificationDrawRequest;
import com.simple.lottery.rpc.activity.booth.result.DrawResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-08 21:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityBoothTest {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBoothTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void testDoDraw() {
        DrawRequest drawReq = new DrawRequest();
        drawReq.setUId("wcx");
        drawReq.setActivityId(100002L);
        DrawResult drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }

    @Test
    public void testDoQuantificationDraw() {
        QuantificationDrawRequest req = new QuantificationDrawRequest();
        req.setUId("wcx");
        req.setTreeId(2110081902L);
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "18");
        }});

        DrawResult drawRes = lotteryActivityBooth.doQuantificationDraw(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }
}
