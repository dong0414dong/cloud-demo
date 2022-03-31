package cn.itcast.feignClient.a05;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @author ：dongdong
 * @date ：Created in 2022/3/29 0029 20:22
 * @description： 这是描述
 * @modified By：
 */

@Configuration
@ComponentScan(value = "cn.itcast.feignClient.a05.component")
public class Config {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cloud_order");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;

    }
}
