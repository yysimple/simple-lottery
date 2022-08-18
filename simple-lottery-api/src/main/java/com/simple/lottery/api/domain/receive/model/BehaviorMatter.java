package com.simple.lottery.api.domain.receive.model;

import lombok.Data;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 消息类型
 *
 * @author: WuChengXing
 * @create: 2022-08-18 21:47
 **/
@Data
public class BehaviorMatter {

    private String openId;
    private String fromUserName;
    private String msgType;
    private String content;
    private String event;
    private Date createTime;
}
