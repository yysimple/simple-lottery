package com.simple.lottery.domain;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.infrastructure.entity.Activity;
import com.simple.lottery.infrastructure.mapper.ActivityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

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

    @Test
    public void testInsert() {
        Activity activity = new Activity();
        activity.setActivityId(100002L);
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
        Activity activity = activityMapper.queryActivityById(100002L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }
}
