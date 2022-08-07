package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.domain.activity.model.vo.AlterStateVO;
import com.simple.lottery.infrastructure.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 扣减活动库存
     *
     * @param activityId 活动ID
     * @return 更新数量
     */
    int subtractionActivityStock(Long activityId);

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<Activity> scanToDoActivityList(Long id);

    /**
     * 更新用户领取活动后，活动库存
     *
     * @param activity  入参
     */
    void updateActivityStock(Activity activity);

    /**
     * 查询活动分页数据数量
     *
     * @param req 入参
     * @return    结果
     */
    Long queryActivityInfoCount(ActivityInfoLimitPageRequest req);

    /**
     * 查询活动分页数据列表
     *
     * @param req   入参
     * @return      结果
     */
    List<ActivityVO> queryActivityInfoList(ActivityInfoLimitPageRequest req);
}
