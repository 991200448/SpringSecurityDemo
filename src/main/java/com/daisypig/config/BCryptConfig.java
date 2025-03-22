package com.daisypig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //工作因子，默认10，最小是4，最大是31，值越大，计算时间越长，安全性越高
        return new BCryptPasswordEncoder();
    }
}
