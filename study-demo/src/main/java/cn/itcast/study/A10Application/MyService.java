package cn.itcast.study.A10Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 20:39
 * @description： 这是描述
 * @modified By：
 */
@Service
public class MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);


    public void foo() {
        logger.debug("myService   foo");
    }
}
