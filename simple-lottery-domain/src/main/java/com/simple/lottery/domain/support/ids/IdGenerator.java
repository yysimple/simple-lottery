package com.simple.lottery.domain.support.ids;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 生成ID接口
 *
 * @author: WuChengXing
 * @create: 2022-07-28 22:10
 **/
public interface IdGenerator {

    /**
     * 获取ID，目前有两种实现方式
     * 1. 雪花算法，用于生成单号
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但指定时间内不能生成太多
     * 3. 随机算法，用于生成策略ID
     *
     * @return ID
     */
    long nextId();
}
