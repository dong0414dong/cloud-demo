package cn.itcast.study.A07Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 12:49
 * @description： 这是描述
 * @modified By：
 */
public class Bean2 implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(Bean2.class);

    @Override
    public void destroy() throws Exception {
        logger.debug("DisposableBean 销毁方法");
    }

    @PreDestroy
    public void destroy1() {
        logger.debug(" @PreDestroy 销毁方法");
    }

    public void destroy2() {
        logger.debug(" destroy2 销毁方法");

    }


    /**
     *
     * 销毁顺序\
     *
     * [DEBUG] 17:12:59.301 [main] c.itcast.study.A07Application.Bean2 -  @PreDestroy 销毁方法
     * [DEBUG] 17:12:59.301 [main] c.itcast.study.A07Application.Bean2 - DisposableBean 销毁方法
     * [DEBUG] 17:12:59.301 [main] c.itcast.study.A07Application.Bean2 -  destroy2 销毁方法
     *
     */
}
