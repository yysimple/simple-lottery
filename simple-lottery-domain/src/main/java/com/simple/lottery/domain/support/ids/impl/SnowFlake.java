package com.simple.lottery.domain.support.ids.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.simple.lottery.domain.support.ids.IdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: hutool 工具包下的雪花算法，15位雪花算法推荐
 *
 * @author: WuChengXing
 * @create: 2022-07-28 22:12
 **/
@Component()
public class SnowFlake implements IdGenerator {
    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        // 0 ~ 31 位，可以采用配置的方式使用
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
    }

    @Override
    public synchronized long nextId() {
        return snowflake.nextId();
    }
}
