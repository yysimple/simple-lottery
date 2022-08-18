package com.simple.lottery.api.domain.receive.service.engine.impl;

import com.simple.lottery.api.domain.receive.model.BehaviorMatter;
import com.simple.lottery.api.domain.receive.model.MessageTextEntity;
import com.simple.lottery.api.domain.receive.service.engine.EngineBase;
import com.simple.lottery.api.domain.receive.service.logic.LogicFilter;
import com.simple.lottery.api.infrastructure.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 消息处理
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:10
 **/
@Service("msgEngineHandle")
public class MsgEngineHandle extends EngineBase {

    @Value("${wx.config.originalid:gh_95b2229b90fb}")
    private String originalId;

    @Override
    public String process(BehaviorMatter request) throws Exception {
        LogicFilter router = super.router(request);
        if (null == router) {
            return null;
        }
        String resultStr = router.filter(request);
        if (StringUtils.isBlank(resultStr)) {
            return "";
        }

        //反馈信息[文本]
        MessageTextEntity res = new MessageTextEntity();
        res.setToUserName(request.getOpenId());
        res.setFromUserName(originalId);
        res.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000L));
        res.setMsgType("text");
        res.setContent(resultStr);
        return XmlUtil.beanToXml(res);
    }
}
