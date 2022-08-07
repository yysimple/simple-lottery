package com.simple.lottery.domain;

import com.alibaba.fastjson.JSON;
import com.simple.lottery.domain.rule.model.request.DecisionMatterRequest;
import com.simple.lottery.domain.rule.model.result.EngineResult;
import com.simple.lottery.domain.rule.service.engine.EngineFilter;
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
 * 功能描述: 规则引擎测试
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTest {

    private final Logger logger = LoggerFactory.getLogger(RuleTest.class);

    @Resource
    private EngineFilter engineFilter;

    @Test
    public void testProcess() {
        DecisionMatterRequest req = new DecisionMatterRequest();
        req.setTreeId(2110081902L);
        req.setUserId("wcx");
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult res = engineFilter.process(req);

        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
