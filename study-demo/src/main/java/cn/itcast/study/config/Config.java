package cn.itcast.study.config;

import cn.itcast.study.bean.Bean1;
import cn.itcast.study.mapper.Mapper1;
import cn.itcast.study.mapper.Mapper2;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/31 0031 21:39
 * @description： 这是描述
 * @modified By：
 */

@Configuration
@ComponentScan(value = "cn.itcast.study")
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
        dataSource.setUrl("jdbc:mysql://196.168.120.101:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }


/*    @Bean
    public MapperFactoryBean<Mapper1> mapper1(
            SqlSessionFactory sqlSessionFactory) {

        MapperFactoryBean<Mapper1> mapperFactoryBean = new MapperFactoryBean<>(Mapper1.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }

    @Bean
    public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper2> factoryBean = new MapperFactoryBean<>(Mapper2.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }*/

}
