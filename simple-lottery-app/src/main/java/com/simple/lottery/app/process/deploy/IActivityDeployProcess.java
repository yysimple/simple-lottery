package com.simple.lottery.app.process.deploy;

import com.simple.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import com.simple.lottery.domain.activity.model.request.ActivityInfoLimitPageRequest;
import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.pagination.util.Page;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动部署接口；查询列表、创建活动、修改活动、删除活动(一般实际场景为逻辑删除)，如果活动的部署需要做一些逻辑校验，那么可以在这一层做编排
 *
 * @author: WuChengXing
 * @create: 2022-08-02 18:36
 **/
public interface IActivityDeployProcess {

    /**
     * 查询活动分页查询聚合对象
     *
     * @param req 请求参数；分页、活动
     * @return 查询结果
     */
    Page<ActivityVO> queryActivityInfoLimitPage(ActivityInfoLimitPageRequest req);
}
