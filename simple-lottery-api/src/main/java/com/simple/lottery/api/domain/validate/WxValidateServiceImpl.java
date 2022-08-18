package com.simple.lottery.api.domain.validate;

import com.simple.lottery.api.app.IWxValidateService;
import com.simple.lottery.api.infrastructure.util.sdk.SignatureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 消息验证
 *
 * @author: WuChengXing
 * @create: 2022-08-18 21:53
 **/
@Service
public class WxValidateServiceImpl implements IWxValidateService {

    @Value("${wx.config.token}")
    private String token;

    @Override
    public boolean checkSign(String signature, String timestamp, String nonce) {
        return SignatureUtil.check(token, signature, timestamp, nonce);
    }
}
