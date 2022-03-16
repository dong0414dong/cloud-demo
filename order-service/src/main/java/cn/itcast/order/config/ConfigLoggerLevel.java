package cn.itcast.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/16 0016 16:24
 * @description： 这是描述
 * @modified By：
 */
@Configuration
public class ConfigLoggerLevel {

    @Bean
    public Logger.Level getLogger() {
        return Logger.Level.BASIC;
    }
}
