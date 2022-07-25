package com.simple.lottery.infrastructure.mapper;

import com.simple.lottery.infrastructure.entity.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-25 22:24
 **/
@Mapper
public interface AwardMapper {

    /**
     * 查询奖品信息
     *
     * @param awardId 奖品ID
     * @return 奖品信息
     */
    Award queryAwardInfo(String awardId);

    /**
     * 插入奖品配置
     *
     * @param list 奖品配置
     */
    void insertList(List<Award> list);
}
