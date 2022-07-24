package com.simple.lottery.rpc;

import com.simple.lottery.rpc.request.ActivityRequest;
import com.simple.lottery.rpc.response.ActivityResponse;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:10
 **/
public interface IActivityBooth {

    /**
     * 查询活动详细信息
     *
     * @param request
     * @return
     */
    ActivityResponse queryActivityById(ActivityRequest request);
}
