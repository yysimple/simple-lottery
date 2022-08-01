package com.simple.lottery.infrastructure.entity;

import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import lombok.Data;

import java.util.Date;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:41
 **/
@Data
public class Activity {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 策略ID
     */
    private Long strategyId;

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
     * 库存剩余
     */
    private Integer stockSurplusCount;

    /**
     * 活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启
     */
    private Integer state;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public static Activity vo2Entity(ActivityVO vo) {
        Activity activity = new Activity();
        activity.setId(vo.getId());
        activity.setActivityId(vo.getActivityId());
        activity.setStrategyId(vo.getStrategyId());
        activity.setActivityName(vo.getActivityName());
        activity.setActivityDesc(vo.getActivityDesc());
        activity.setBeginDateTime(vo.getBeginDateTime());
        activity.setEndDateTime(vo.getEndDateTime());
        activity.setStockCount(vo.getStockCount());
        activity.setTakeCount(vo.getTakeCount());
        activity.setStockSurplusCount(vo.getStockSurplusCount());
        activity.setState(vo.getState());
        activity.setCreator(vo.getCreator());
        activity.setCreateTime(vo.getCreateTime());
        activity.setUpdateTime(vo.getUpdateTime());
        return activity;
    }
}
