package cn.itcast.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/19 0019 11:21
 * @description： 这是描述
 * @modified By：
 */

@SpringBootApplication
@MapperScan("cn.itcast.hotel.mapper")
public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class);
    }


    @Bean
    public RestHighLevelClient getClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        HttpHost.create("http://192.168.120.101:9200")
                )
        );
    }
}
