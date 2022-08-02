package com.simple.lottery.app.process.draw.result;

import com.simple.lottery.common.entity.Result;
import com.simple.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-02 18:37
 **/
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() {
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}
