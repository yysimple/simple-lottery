package com.simple.lottery.api.app;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述: 处理接收信息
 *
 * @author: WuChengXing
 * @create: 2022-08-17 21:51
 **/
public interface IWxReceiveService {

    /**
     * 接收信息
     *
     * @param behaviorMatter    入参
     * @return                  出惨
     * @throws Exception        异常
     */
    String doReceive(BehaviorMatter behaviorMatter) throws Exception;
}
