package com.simple.lottery.erp.services;

import com.simple.lottery.common.entity.SimpleResponse;
import com.simple.lottery.erp.app.IActivityService;
import com.simple.lottery.erp.domain.model.ActivityListPageRequest;
import com.simple.lottery.erp.infrastructure.common.EasyResult;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.pagination.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述: c层
 *
 * @author: WuChengXing
 * @create: 2022-08-17 11:03
 **/
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Resource
    private IActivityService activityService;

    @GetMapping("allActivity")
    public SimpleResponse<Page<ActivityDto>> queryActivityListPage(String erpId, Integer pageIndex, Integer pageSize) {
        try {
            logger.info("查询活动列表数据 erpId:{}  pageIndex：{} pageSize：{}", erpId, pageIndex, pageSize);
            pageSize = null == pageSize ? 10 : pageSize;
            ActivityListPageRequest req = new ActivityListPageRequest();
            req.setErpId(erpId);
            req.setPageIndex(pageIndex);
            req.setPageSize(pageSize);
            req.setErpId("wcx");
            return new SimpleResponse<>(activityService.queryActivityListPage(req));
        } catch (Exception e) {
            logger.info("查询活动列表数据 pageIndex：{} pageSize：{}", pageIndex, pageSize);
            return new SimpleResponse<>();
        }
    }
}

