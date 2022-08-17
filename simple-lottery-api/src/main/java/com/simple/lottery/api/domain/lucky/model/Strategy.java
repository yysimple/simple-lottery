package com.simple.lottery.api.domain.lucky.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述: 策略
 *
 * @author: WuChengXing
 * @create: 2022-08-17 21:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Strategy {
    private String text;
    private String top;
}
