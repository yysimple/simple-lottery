package com.simple.lottery.rpc.activity.booth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖请求
 *
 * @author: WuChengXing
 * @create: 2022-08-07 22:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawRequest {

    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动ID
     */
    private Long activityId;
}
