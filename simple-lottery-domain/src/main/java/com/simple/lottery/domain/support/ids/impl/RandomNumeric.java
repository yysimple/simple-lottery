package com.simple.lottery.domain.support.ids.impl;

import com.simple.lottery.domain.support.ids.IdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 工具类生成 org.apache.commons.lang3.RandomStringUtils
 *
 * @author: WuChengXing
 * @create: 2022-07-28 22:14
 **/
@Component
public class RandomNumeric implements IdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
