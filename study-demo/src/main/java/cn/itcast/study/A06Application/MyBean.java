package cn.itcast.study.A06Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 10:43
 * @description： 这是描述
 * @modified By：
 */
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(MyBean.class);

    @Override
    public void setBeanName(String s) {
        logger.debug("当前的bean name是" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("当前bean 的容器是:" + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("当前bean执行初始化操作");
    }

    @Autowired  //扩展功能会失效
    private void aaa(ApplicationContext applicationContext) {
        logger.debug("当前bean" + this + "使用@@Autowired");

    }

    @PostConstruct    //扩展功能会失效
    private void init() {
        logger.debug("当前bean" + this + "@PostConstruct");
    }
}
