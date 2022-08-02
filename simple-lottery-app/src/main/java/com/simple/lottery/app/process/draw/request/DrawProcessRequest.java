package com.simple.lottery.app.process.draw.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-02 18:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawProcessRequest {

    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动ID
     */
    private Long activityId;
}
