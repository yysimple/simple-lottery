package com.simple.lottery.rpc.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动请求入参
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:00
 **/
@Data
public class ActivityRequest implements Serializable {

    private static final long serialVersionUID = -5058341441037222916L;

    private Long activityId;
}
