package cn.itcast.feignClient.beanFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/28 0028 22:01
 * @description： 这是描述
 * @modified By：
 */
public class Bean7 {

    private final static Logger logger = LoggerFactory.getLogger(Bean7.class);

    private Bean5 bean5;

    @Autowired
    private void setBean5(Bean5 bean5) {
        logger.debug("@Autowired 生效:{}", bean5);
        this.bean5 = bean5;
    }

    private Bean6 bean6;

    @Resource
    private void setBean6(Bean6 bean6) {
        logger.debug("@Resource  生效 :{}", bean6);
        this.bean6 = bean6;
    }

    private String home;

    @Autowired
    private void setHome(@Value("${JAVA_HOME}") String home) {
        logger.debug("@Value 生效 :{}", home);
        this.home = home;
    }


    @Override
    public String toString() {
        return "Bean7{" +
                "bean5=" + bean5 +
                ", bean6=" + bean6 +
                ", home='" + home + '\'' +
                '}';
    }
}
