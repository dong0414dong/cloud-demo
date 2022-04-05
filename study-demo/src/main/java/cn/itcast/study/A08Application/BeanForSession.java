package cn.itcast.study.A08Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 17:30
 * @description： 这是描述
 * @modified By：
 */
@Component
@Scope(value = "session")
public class BeanForSession {

    private static final Logger logger = LoggerFactory.getLogger(BeanForSession.class);

    @PreDestroy
    public void destroy() {
        logger.debug("destroy");
    }

}
