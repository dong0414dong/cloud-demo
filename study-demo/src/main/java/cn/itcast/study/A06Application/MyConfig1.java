package cn.itcast.study.A06Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 10:58
 * @description： 这是描述
 * @modified By：
 */


@Configuration
public class MyConfig1 {


    private static final Logger logger = LoggerFactory.getLogger(MyConfig1.class);


    @Autowired
    private void addApplication(ApplicationContext applicationContext) {
        logger.debug("当前bean" + this + "注入applicationContext");
    }

    @PostConstruct
    private void init() {
        logger.debug("当前bean" + this + "执行初始化");
    }

    @Bean   //beanFactory
    public BeanFactoryPostProcessor processor() {
        return beanFactory -> {
            logger.debug("执行 processor");
        };
    }

}
