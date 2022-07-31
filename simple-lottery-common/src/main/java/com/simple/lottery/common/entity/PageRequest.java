package com.simple.lottery.common.entity;

import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 分页请求参数
 *
 * @author: WuChengXing
 * @create: 2022-07-24 02:22
 **/
@Data
public class PageRequest {

    /**
     * 当前页
     */
    private Integer pageIndex;

    /**
     * 当前页大小
     */
    private Integer pageSize;
}
