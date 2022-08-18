package com.simple.lottery.api.domain.receive.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 消息类型
 *
 * @author: WuChengXing
 * @create: 2022-08-18 21:47
 **/
@Data
public class MessageTextEntity {

    @XStreamAlias("MsgId")
    private String msgId;
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("Content")
    private String content;
    @XStreamAlias("Event")
    private String event;
    @XStreamAlias("EventKey")
    private String eventKey;
}
