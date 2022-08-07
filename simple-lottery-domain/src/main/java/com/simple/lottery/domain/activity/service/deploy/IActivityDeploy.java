package com.simple.lottery.domain.activity.service.deploy;

import com.simple.lottery.domain.activity.model.request.ActivityConfigRequest;
import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.pagination.util.Page;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 部署活动配置接口
 *
 * @author: WuChengXing
 * @create: 2022-07-31 13:33
 **/
public interface IActivityDeploy {

    /**
     * 创建活动信息
     *
     * @param req 活动配置信息
     */
    void createActivity(ActivityConfigRequest req) throws Exception;

    /**
     * 修改活动信息
     *
     * @param req 活动配置信息
     */
    void updateActivity(ActivityConfigRequest req);

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     * <p>
     * 通过 -> 时间符合时 -> 活动中
     * 活动中 -> 时间到期时 -> 关闭
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<ActivityVO> scanToDoActivityList(Long id);

    /**
     * 分页查询活动信息
     *
     * @param request
     * @return
     */
    Page<ActivityVO> queryActivityInfoLimitPage(ActivityInfoLimitPageRequest request);
}
