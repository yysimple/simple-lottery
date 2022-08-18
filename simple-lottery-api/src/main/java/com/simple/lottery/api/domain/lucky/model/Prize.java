package com.simple.lottery.api.domain.lucky.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-17 21:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prize {

    private List<Strategy> fonts = new ArrayList<>();
    private String background;

    public Prize(Strategy fonts, String background) {
        this.fonts.add(fonts);
        this.background = background;
    }
}
