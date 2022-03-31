package cn.itcast.feignClient.a05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/29 0029 20:26
 * @description： 这是描述
 * @modified By：
 */
//@Component
public class Bean2 {

    private static final Logger logger = LoggerFactory.getLogger(Bean.class);

    public Bean2() {
        logger.debug("bean2 被spring 管理了");
    }

}
