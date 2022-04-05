package cn.itcast.study.A10Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 20:37
 * @description： 这是描述
 * @modified By：
 */
@SpringBootApplication
public class A10Application {
    private static final Logger logger = LoggerFactory.getLogger(A10Application.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(A10Application.class, args);

        MyService bean = context.getBean(MyService.class);

        //此时获取到 bean  class cn.itcast.study.A10Application.MyService
        //竟然不是 代理类    这是因为 @Aspect  注解 修改了原始类   并不是spring来管理的
        logger.info(bean.getClass() + "");


    }
}
