package com.simple.lottery.rpc.activity.deploy;

import com.simple.lottery.rpc.activity.deploy.request.ActivityPageRequest;
import com.simple.lottery.rpc.activity.deploy.result.ActivityResult;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖活动部署服务接口
 *
 * @author: WuChengXing
 * @create: 2022-08-07 15:45
 **/
public interface ILotteryActivityDeploy {

    /**
     * 通过分页查询活动列表信息，给ERP运营使用
     *
     * @param req   查询参数
     * @return      查询结果
     */
    ActivityResult queryActivityListByPageForErp(ActivityPageRequest req);
}
