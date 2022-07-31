package com.simple.lottery.domain.strategy.model.result;

import com.simple.lottery.common.enums.DrawState;
import com.simple.lottery.domain.strategy.model.vo.DrawAwardVO;
import lombok.Data;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 抽奖结果
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:24
 **/
@Data
public class DrawResult {
    /**
     * 用户ID
     */
    private String uId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
     */
    private Integer drawState = DrawState.FAIL.getCode();

    /**
     * 中奖奖品信息
     */
    private DrawAwardVO drawAwardInfo;

    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardVO drawAwardInfo) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.drawAwardInfo = drawAwardInfo;
    }

}
