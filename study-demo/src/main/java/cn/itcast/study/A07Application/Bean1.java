package cn.itcast.study.A07Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 12:49
 * @description： 这是描述
 * @modified By：
 */
public class Bean1 implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(Bean1.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("InitializingBean 提供的内置初始化方法");
    }

    @PostConstruct
    public void init1() {
        logger.debug("@PostConstruct 注解提供的初始化方法");
    }

    public void init() {
        logger.debug("初始化方法3");
    }


    //这三个初始化的顺序 是 :
    /**
     * [DEBUG] 16:57:07.836 [main] c.itcast.study.A07Application.Bean1 - @PostConstruct 注解提供的初始化方法
     * [DEBUG] 16:57:07.836 [main] c.itcast.study.A07Application.Bean1 - InitializingBean 提供的内置初始化方法
     * [DEBUG] 16:57:07.837 [main] c.itcast.study.A07Application.Bean1 - 初始化方法3
     */
}
