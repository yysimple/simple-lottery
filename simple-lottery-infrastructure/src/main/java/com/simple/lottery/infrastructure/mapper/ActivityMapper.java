package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.infrastructure.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

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

    void insert(Activity activity);

    Activity queryActivityById(Long activityId);
}
