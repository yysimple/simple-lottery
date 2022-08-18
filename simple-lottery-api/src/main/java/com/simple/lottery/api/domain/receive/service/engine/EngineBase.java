package com.simple.lottery.api.domain.receive.service.engine;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;
import com.simple.lottery.api.domain.receive.service.logic.LogicFilter;

import java.util.Map;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 引擎基类
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:05
 **/
public class EngineBase extends EngineConfig implements Engine {

    @Override
    public String process(BehaviorMatter request) throws Exception {
        throw new RuntimeException("未实现消息引擎服务");
    }

    /**
     * 消息类型&业务内容路由器
     *
     * @param request 消息内容
     * @return 返回消息处理器
     */
    protected LogicFilter router(BehaviorMatter request) {

        Map<String, LogicFilter> logicGroup = logicFilterMap.get(request.getMsgType());

        // 事件处理
        if ("event".equals(request.getMsgType())) {
            return logicGroup.get(request.getEvent());
        }

        // 内容处理
        if ("text".equals(request.getMsgType())) {
            return logicGroup.get("lottery");
        }

        return null;
    }
}
