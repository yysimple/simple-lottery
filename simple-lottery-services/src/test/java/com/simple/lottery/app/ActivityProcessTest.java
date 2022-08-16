package com.simple.lottery.app;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.app.process.draw.IActivityDrawProcess;
import com.simple.lottery.app.process.draw.request.DrawProcessRequest;
import com.simple.lottery.app.process.draw.result.DrawProcessResult;
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
 * 功能描述: 抽奖发消息测试
 *
 * @author: WuChengXing
 * @create: 2022-08-16 10:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityProcessTest {

    private final Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityDrawProcess activityProcess;

    @Test
    public void testDoDrawProcess() {
        DrawProcessRequest req = new DrawProcessRequest();
        req.setUId("wcx");
        req.setActivityId(100002L);
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(req);

        logger.info("请求入参：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawProcessResult));
    }
}
