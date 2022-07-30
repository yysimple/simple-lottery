package com.simple.lottery.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 变更活动状态对象
 *
 * @author: WuChengXing
 * @create: 2022-07-30 23:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterStateVO {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 更新前状态
     */
    private Integer beforeState;

    /**
     * 更新后状态
     */
    private Integer afterState;

}
