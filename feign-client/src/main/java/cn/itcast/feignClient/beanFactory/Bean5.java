package cn.itcast.feignClient.beanFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/28 0028 21:51
 * @description： 这是描述
 * @modified By：
 */

//@Component
public class Bean5 {
    @Autowired
    private Bean6 bean6;

    public Bean6 getBean6() {
        return bean6;
    }

    public void setBean6(Bean6 bean6) {
        this.bean6 = bean6;
    }
}
