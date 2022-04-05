package cn.itcast.order;

import cn.itcast.feignClient.feign.UserFeignClient;
import cn.itcast.order.config.ConfigLoggerLevel;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
//@EnableApolloConfig
@SpringBootApplication
//  defaultConfiguration  指定日志级别    clients 指定需要调用的feign客户端
@EnableFeignClients(clients = UserFeignClient.class, defaultConfiguration = ConfigLoggerLevel.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

/*    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }*/
}