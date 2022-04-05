package cn.itcast.study.A06Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 12:42
 * @description： 这是描述
 * @modified By：
 */
public class MyConfig2 implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(MyConfig2.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      logger.debug("注入applicationContext");
    }

    @Bean   //beanFactory
    public BeanFactoryPostProcessor processor() {
        return beanFactory -> {
            logger.debug("执行 processor");
        };
    }
}
