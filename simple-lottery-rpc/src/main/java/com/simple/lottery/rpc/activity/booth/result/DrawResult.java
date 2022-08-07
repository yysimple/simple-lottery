package com.simple.lottery.rpc.activity.booth.result;

import com.simple.lottery.common.entity.Result;
import com.simple.lottery.rpc.activity.booth.dto.AwardDto;
import lombok.*;

import java.io.Serializable;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖结果
 *
 * @author: WuChengXing
 * @create: 2022-08-07 22:12
 **/
@Setter
@Getter
@ToString
public class DrawResult extends Result implements Serializable {

    private static final long serialVersionUID = 1360415306464323749L;

    private AwardDto awardDto;

    public DrawResult(String code, String info) {
        super(code, info);
    }

}
