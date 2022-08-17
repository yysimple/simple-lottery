package com.simple.lottery.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 项目: simple-lottery-erp
 * <p>
 * 功能描述: erp启动类
 *
 * @author: WuChengXing
 * @create: 2022-08-17 10:34
 **/
@SpringBootApplication
public class SimpleLotteryErpApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SimpleLotteryErpApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleLotteryErpApplication.class, args);
    }
}
