package com.simple.lottery.erp.app;

import com.simple.lottery.common.entity.SimpleResponse;
import com.simple.lottery.erp.domain.model.ActivityListPageRequest;
import com.simple.lottery.erp.infrastructure.common.EasyResult;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.pagination.util.Page;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述: 活动接口
 *
 * @author: WuChengXing
 * @create: 2022-08-17 10:50
 **/
public interface IActivityService {

    /**
     * 查询活动分页数据
     *
     * @param req 入参
     * @return 结果
     */
    Page<ActivityDto> queryActivityListPage(ActivityListPageRequest req);
}
