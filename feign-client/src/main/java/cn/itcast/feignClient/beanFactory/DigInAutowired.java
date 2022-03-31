package cn.itcast.feignClient.beanFactory;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/28 0028 21:54
 * @description： 分析  AutowiredAnnotationBeanPostProcessor  的作用
 * @modified By：
 */
public class DigInAutowired {

    public static void main(String[] args) throws Throwable {

        //创建一个工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注入两个bean
        beanFactory.registerSingleton("bean5", new Bean5());
        beanFactory.registerSingleton("bean6", new Bean6());

        //这里处理 @Value
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());

        //处理${}
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        //手动创建  AutowiredAnnotationBeanPostProcessor

        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();

        //将beanFactory设置给  postProcessor
        postProcessor.setBeanFactory(beanFactory);

        Bean7 bean7 = new Bean7();
        System.out.println("bean7:" + bean7); //此时bean7的属性为空 因为@Autowired  @Value 没有执行
        /**
         * bean7:Bean7{bean5=null, bean6=null, home='null'}
         */

        //需要执行  postProcessProperties
        postProcessor.postProcessProperties(null, bean7, "bean7");
        //再次打印 bean7
        System.out.println("bean7:" + bean7);
        /**
         * 22:13:20.682 [main] DEBUG cn.itcast.feignClient.beanFactory.Bean7 - @Autowired 生效:cn.itcast.feignClient.beanFactory.Bean5@4218d6a3
         * 22:13:20.692 [main] DEBUG cn.itcast.feignClient.beanFactory.Bean7 - @Value 生效 :${JAVA_HOME}
         * bean7:Bean7{bean5=cn.itcast.feignClient.beanFactory.Bean5@4218d6a3, bean6=null, home='${JAVA_HOME}'}
         */

        //那么问题来了 具体过程怎么实现的呢?  通过反射测试看看
        Method findAutowiredAnnotation =
                AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiredAnnotation.setAccessible(true);
        //这里就是获取  Bean7 里面加@Autowired @Value 注解的属性
        InjectionMetadata injectionMetadata = (InjectionMetadata)
                findAutowiredAnnotation.invoke(postProcessor, "bean7", Bean7.class, null);

        //打印一下
        System.out.println(injectionMetadata);

        //调用 injectionMetadata 注入 这是按照类型注入
        injectionMetadata.inject(bean7, "bean7", null);
        System.out.println(bean7);

        //如何按照类型 查找值?
        Field bean5 = Bean7.class.getDeclaredField("bean5");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(bean5, false);

        //此时根  属性 bean5 的类型 去寻找 ,然后反射调用注入属性
        Object o = beanFactory.doResolveDependency(dependencyDescriptor, null, null, null);
        System.out.println(o);

    }
}
