package com.simple.lottery.api.domain.receive.service.engine;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:04
 **/
public interface Engine {

    /**
     * 过滤器
     * @param request       入参
     * @return              出参
     * @throws Exception    异常
     */
    String process(final BehaviorMatter request) throws Exception;

}
