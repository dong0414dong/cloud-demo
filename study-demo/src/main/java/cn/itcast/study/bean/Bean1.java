package cn.itcast.study.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/31 0031 21:39
 * @description： 这是描述
 * @modified By：
 */
@Component
public class Bean1 {

    private final static Logger logger = LoggerFactory.getLogger(Bean1.class);

    public Bean1() {
        logger.debug("bean1  被 spring  管理了");
    }
}
