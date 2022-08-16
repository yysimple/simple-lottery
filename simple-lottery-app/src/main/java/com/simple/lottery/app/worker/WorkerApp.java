package com.simple.lottery.app.worker;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-02 18:26
 **/
@Component
public class WorkerApp {

    private final Logger logger = LoggerFactory.getLogger(WorkerApp.class);

    @XxlJob("workerAppJobHandler")
    public void workerAppJobHandler() throws Exception {
        logger.warn("我是测试的定时任务，当前时间：{}", new Date());
    }
}
