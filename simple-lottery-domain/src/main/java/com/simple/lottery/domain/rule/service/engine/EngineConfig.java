package com.simple.lottery.domain.rule.service.engine;

import com.simple.lottery.domain.rule.service.logic.LogicFilter;
import com.simple.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import com.simple.lottery.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 规则配置
 *
 * @author: WuChengXing
 * @create: 2022-08-07 12:39
 **/
public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;
    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }
}
