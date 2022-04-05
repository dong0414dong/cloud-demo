package cn.itcast.study.A09Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 19:15
 * @description： 这是描述
 * @modified By：
 */

@ComponentScan("cn.itcast.study.A09Application")
public class A09Application {

    private static final Logger logger = LoggerFactory.getLogger(A09Application.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(A09Application.class);
        E bean = context.getBean(E.class);


        logger.info("{}", bean.getF1()+"你好");
        logger.info("{}", bean.getF1());
        logger.info("{}", bean.getF1());

        //如果在注入F1的时候设置为 @Lazy  咱们可以看看 F1是什么
        //这是一个F1的代理对象
        logger.info(bean.getF1().getClass()+"");
        //  [INFO ] 20:26:40.398 [main] c.i.s.A09Application.A09Application - class cn.itcast.study.A09Application.F1$$EnhancerBySpringCGLIB$$f1b4a7d8

        //第二种解决办法   在被注入的类上 添加 proxyMode = ScopedProxyMode.TARGET_CLASS
        logger.info("{}", bean.getF2());
        logger.info("{}", bean.getF2());


        //第三种解决办法   注入的时候 注入 ObjectFactory   工厂方式

        logger.info("{}", bean.getF3());
        logger.info("{}", bean.getF3());

        //第四种解决办法   通过容器获取  ApplicationContext context;

        logger.info("{}", bean.getF4());
        logger.info("{}", bean.getF4());

        //这几种解决办法 都是同一个原理   :推迟 scope bean的获取时间
        context.close();

    }
}
