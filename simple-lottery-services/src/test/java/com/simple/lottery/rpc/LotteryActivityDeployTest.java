package com.simple.lottery.rpc;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.rpc.activity.deploy.ILotteryActivityDeploy;
import com.simple.lottery.rpc.activity.deploy.request.ActivityPageRequest;
import com.simple.lottery.rpc.activity.deploy.result.ActivityResult;
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
 * 功能描述: rpc接口测试
 *
 * @author: WuChengXing
 * @create: 2022-08-07 20:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityDeployTest {

    private final Logger logger = LoggerFactory.getLogger(LotteryActivityDeployTest.class);

    @Resource
    private ILotteryActivityDeploy lotteryActivityDeploy;

    @Test
    public void testQueryActivityListByPageForErp() {
        ActivityPageRequest req = new ActivityPageRequest();
        req.setPageIndex(1);
        req.setPageSize(2);
        req.setErpId("wcx");

        ActivityResult res = lotteryActivityDeploy.queryActivityListByPageForErp(req);

        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
