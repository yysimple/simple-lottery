package com.simple.lottery.rpc.activity.deploy.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动返回值
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:00
 **/
@Data
public class ActivityResult implements Serializable {

    private static final long serialVersionUID = -3330962358009663242L;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 开始时间
     */
    private Date beginDateTime;

    /**
     * 结束时间
     */
    private Date endDateTime;

    /**
     * 库存
     */
    private Integer stockCount;

    /**
     * 每人可参与次数
     */
    private Integer takeCount;

    /**
     * 活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启
     */
    private Integer state;
}
