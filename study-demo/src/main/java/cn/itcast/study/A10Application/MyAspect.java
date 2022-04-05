package cn.itcast.study.A10Application;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 20:40
 * @description： 这是描述
 * @modified By：
 */

@Aspect   //该切面并没有被spring管理
public class MyAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* cn.itcast.study.A10Application.MyService.foo())")
    public void before() {
        logger.debug(" MyAspect before()");
    }
}
