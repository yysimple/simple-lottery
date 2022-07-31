package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.domain.activity.model.vo.AlterStateVO;
import com.simple.lottery.infrastructure.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:41
 **/
@Mapper
public interface ActivityMapper {

    /**
     * 插入一一条活动信息
     *
     * @param activity
     */
    void insert(Activity activity);

    /**
     * 根据id查询对应的额活动详情
     *
     * @param activityId
     * @return
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     *
     * @param alterStateVO [activityId、beforeState、afterState]
     * @return 更新数量
     */
    int alterState(AlterStateVO alterStateVO);
}
