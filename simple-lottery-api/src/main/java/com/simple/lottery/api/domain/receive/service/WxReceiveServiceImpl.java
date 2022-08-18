package com.simple.lottery.api.domain.receive.service;

import com.simple.lottery.api.app.IWxReceiveService;
import com.simple.lottery.api.domain.receive.model.BehaviorMatter;
import com.simple.lottery.api.domain.receive.service.engine.Engine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-18 21:51
 **/
@Service
public class WxReceiveServiceImpl implements IWxReceiveService {

    @Resource(name = "msgEngineHandle")
    private Engine msgEngineHandle;

    @Override
    public String doReceive(BehaviorMatter behaviorMatter) throws Exception {
        return msgEngineHandle.process(behaviorMatter);
    }

}
