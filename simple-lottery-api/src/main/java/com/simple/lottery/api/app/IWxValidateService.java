package com.simple.lottery.api.app;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 验证
 *
 * @author: WuChengXing
 * @create: 2022-08-18 22:00
 **/
public interface IWxValidateService {

    /**
     * 验签
     * @param signature 签名
     * @param timestamp 时间
     * @param nonce     当前
     * @return          结果
     */
    boolean checkSign(String signature, String timestamp, String nonce);
}
