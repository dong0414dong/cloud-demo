package cn.itcast.douluo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/3 0003 12:55
 * @description： 这是描述
 * @modified By：
 */
@MapperScan("cn.itcast.douluo.mapper")
@SpringBootApplication
@EnableEurekaClient
public class DouluoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DouluoApplication.class);
    }
}
