package cn.itcast.study.A09Application;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class E {

    @Lazy
    @Autowired
    private F1 f1;

    /**
     * F1是多例的   ,从E获取的时候每次获取却是 一样的 ,因为 F1只注入了一次
     * <p>
     * 怎么解决呢?   F1注入的时候 设置为  @Lazy   注入为F1的代理对象,每次使用代理对象创建新的对象
     * [INFO ] 20:21:47.461 [main] c.i.s.A09Application.A09Application - cn.itcast.study.A09Application.F1@55dfebeb你好
     * [INFO ] 20:21:47.472 [main] c.i.s.A09Application.A09Application - cn.itcast.study.A09Application.F1@55dfebeb
     * [INFO ] 20:21:47.472 [main] c.i.s.A09Application.A09Application - cn.itcast.study.A09Application.F1@55dfebeb
     */

    @Autowired
    private F2 f2;

    @Autowired
    private ObjectFactory<F3> f3;

    @Autowired
    private ApplicationContext context;

    public F1 getF1() {
        return f1;
    }

    public F2 getF2() {
        return f2;
    }

    public F3 getF3() {
        return f3.getObject();
    }

    public F4 getF4() {
        return context.getBean(F4.class);
    }
}
