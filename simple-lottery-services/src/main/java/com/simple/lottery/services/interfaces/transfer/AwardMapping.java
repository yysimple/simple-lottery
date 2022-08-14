package com.simple.lottery.services.interfaces.transfer;

import com.simple.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.simple.lottery.rpc.activity.booth.dto.AwardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-07 15:31
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO, AwardDto> {

    @Mapping(target = "userId", source = "UId")
    @Override
    AwardDto sourceToTarget(DrawAwardVO drawAwardVO);

    @Override
    DrawAwardVO targetToSource(AwardDto awardDto);
}
