package com.simple.lottery.rpc.activity.deploy.request;

import com.simple.lottery.common.entity.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-07 15:41
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityPageRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -4565454188804455366L;

    /**
     * ERP ID，记录谁在操作
     */
    private String erpId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;
}
