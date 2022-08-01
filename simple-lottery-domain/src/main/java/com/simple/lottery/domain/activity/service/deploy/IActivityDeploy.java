package com.simple.lottery.domain.activity.service.deploy;

import com.simple.lottery.domain.activity.model.request.ActivityConfigRequest;

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
}
