package com.simple.lottery.services.interfaces.transfer;

import com.simple.lottery.domain.activity.model.vo.ActivityVO;
import com.simple.lottery.rpc.activity.deploy.dto.ActivityDto;
import com.simple.pagination.util.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 活动对象转换配置
 *
 * @author: WuChengXing
 * @create: 2022-08-07 15:20
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityMapping extends IMapping<ActivityVO, ActivityDto> {

    /**
     * vo转换成dto
     *
     * @param activityVOS
     * @return
     */
    @Override
    List<ActivityDto> sourceToTarget(List<ActivityVO> activityVOS);

    /**
     * 分页拷贝
     *
     * @param page
     * @return
     */
    @Override
    Page<ActivityDto> sourceToTargetPage(Page<ActivityVO> page);
}
