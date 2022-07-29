package com.simple.lottery.domain.strategy.service.draw;

import com.simple.lottery.domain.strategy.annotation.Strategy;
import com.simple.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖统一配置信息类
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:57
 **/
public class DrawConfig {

    @Resource
    private final List<IDrawAlgorithm> algorithmList = new ArrayList<>();

    /**
     * 抽奖策略组
     */
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        algorithmList.forEach(algorithm -> {
            Strategy strategy = AnnotationUtils.findAnnotation(algorithm.getClass(), Strategy.class);
            if (null != strategy) {
                drawAlgorithmGroup.put(strategy.strategyMode().getCode(), algorithm);
            }
        });
    }
}
