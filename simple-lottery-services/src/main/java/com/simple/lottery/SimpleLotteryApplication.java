package com.simple.lottery;

import com.simple.rpc.springboot.annotaton.SimpleRpcScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-07-24 17:12
 **/
@SpringBootApplication
@SimpleRpcScan(basePackages = "com.simple.lottery.services.interfaces.rpc")
@MapperScan(basePackages = "com.simple.lottery.infrastructure.mapper")
public class SimpleLotteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleLotteryApplication.class, args);
    }
}
