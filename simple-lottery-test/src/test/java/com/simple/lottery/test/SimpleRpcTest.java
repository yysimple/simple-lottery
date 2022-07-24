package com.simple.lottery.test;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.rpc.IActivityBooth;
import com.simple.lottery.rpc.dto.ActivityDto;
import com.simple.lottery.rpc.request.ActivityRequest;
import com.simple.rpc.common.annotation.SimpleRpcReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 18:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleRpcTest {

    private Logger logger = LoggerFactory.getLogger(SimpleRpcTest.class);

    @SimpleRpcReference
    private IActivityBooth activityBooth;

    @Test
    public void test_rpc() {
        ActivityRequest req = new ActivityRequest();
        req.setActivityId(100002L);
        ActivityDto result = activityBooth.queryActivityById(req);
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }
}
