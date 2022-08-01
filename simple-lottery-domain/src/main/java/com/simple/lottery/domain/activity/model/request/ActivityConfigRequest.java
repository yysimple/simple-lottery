package com.simple.lottery.domain.activity.model.request;

import com.simple.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动配置请求对象
 *
 * @author: WuChengXing
 * @create: 2022-07-30 23:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityConfigRequest {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动配置信息
     */
    private ActivityConfigRich activityConfigRich;
}
