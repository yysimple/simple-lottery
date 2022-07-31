package com.simple.lottery.domain.activity.model.aggregates;

import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import lombok.Data;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动分页查询聚合对象
 *
 * @author: WuChengXing
 * @create: 2022-07-31 15:59
 **/
@Data
public class ActivityInfoLimitPageRich {

    private Long count;
    private List<ActivityVO> activityVOList;

    public ActivityInfoLimitPageRich() {
    }

    public ActivityInfoLimitPageRich(Long count, List<ActivityVO> activityVOList) {
        this.count = count;
        this.activityVOList = activityVOList;
    }
}
