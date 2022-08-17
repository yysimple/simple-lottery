package com.simple.lottery.erp.domain.model;

import com.simple.lottery.common.entity.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-17 10:52
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityListPageRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -1890879143115502832L;
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
