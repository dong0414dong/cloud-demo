package cn.itcast.study.A06Application;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 10:43
 * @description： 这是描述
 * @modified By：
 */
public class A06Application {

    public static void main(String[] args) {


        GenericApplicationContext context = new GenericApplicationContext();


        //context.registerBean("myBean", MyBean.class);
        //context.registerBean("myConfig1", MyConfig1.class);
        context.registerBean("myConfig2", MyConfig2.class);
        //@Autowired   @PostConstruct 可能会失效
        //需要手动加后置处理  这个两个 注解才会生效
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        context.registerBean(ConfigurationClassPostProcessor.class);

        context.refresh();
        //这部的主要操作流程
        //首先  1:beanFactory  后置处理器  2:添加bean处理器  3:初始化单例
        /**
         * 如果类里面没有 BeanFactoryPostProcessor.class
         *
         * 正常的流程是:
         * applicationContext 首先 执行BeanFactoryPostProcessor
         *                      再执行 BeanPostProcessor
         *                      然后 创建和初始化
         *                          创建初始化包括: 1:依赖注入扩展 @Value  @Autowired
         *                                         2:初始化扩展  @PostConstruct
         *
         *                                         3:执行 @Aware 以及 InitialingBean
         *
         *                                         4:创建成功
         * 如果该类是个配置类  里面包含 BeanFactoryPostProcessor
         *
         * 那么执行的顺序就不是这样了  第一步肯定需要把配置类先初始化实例化
         *     1:执行 Aware   InitialingBean
         *     2:创建成功
         *
         *     创建完之后才发现 有后置处理器的注解存在
         *     才开始执行 后置处理器
         *
         *     初始化创建执行在前,注册后置处理器在后 那么配置类里面的后置处理器就失效了
         *
         *
         *
         *
         */

        context.close();
    }
}
