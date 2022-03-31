package cn.itcast.feignClient.beanFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author ：dongdong
 * @date ：Created in 2022/3/27 0027 18:36
 * @description： 这是描述
 * @modified By：
 */
public class TestBeanFactory {

    public static void main(String[] args) {

        //首先获取 beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition
                singleton = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();

        beanFactory.registerBeanDefinition("config", singleton);

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String string : beanDefinitionNames) {
            System.out.println(string);
        }

        //此时有疑问 为什么只有我们自己手动添加 config  没有出现 bean1  bean2 这些?
        //因为这是后置处理器来处理的,所以我们需要把后置处理器添加到 beanFactory

        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        String[] beanDefinitionNames1 = beanFactory.getBeanDefinitionNames();

        for (String string : beanDefinitionNames1) {
            System.out.println(string);
        }

        //此时虽然添加了  后置处理器,但是后置处理器还没开始工作,如何让他们工作?

        //首先需要拿到这些 beanFactory 后置处理器  根据类型拿到
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        beansOfType.values().stream().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        String[] beanDefinitionNames2 = beanFactory.getBeanDefinitionNames();

        for (String string : beanDefinitionNames2) {
            System.out.println(string);
        }

        //这里添加  bean 后置处理器   处理 @Autowired  @Resource 这些
        /**
         * =====>org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@51e69659
         * =====>org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@47e2e487
         */
        Map<String, BeanPostProcessor> beansOfType1 = beanFactory.getBeansOfType(BeanPostProcessor.class);

/*        beansOfType1.values().stream().forEach(beanPostProcessor -> {
            System.out.println("=====>" + beanPostProcessor);
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        });*/

       //改变 后处理器的顺序
        beansOfType1.values().stream().sorted(beanFactory.getDependencyComparator())
                .forEach(beanPostProcessor -> {
                    System.out.println("=====>" + beanPostProcessor);
                    beanFactory.addBeanPostProcessor(beanPostProcessor);
                });
        Bean1 bean1 = beanFactory.getBean(Bean2.class).getBean1();

        System.out.println(bean1);

        System.out.println("Inter类型" + beanFactory.getBean(Bean1.class).getBean3());


    }


    //@Configuration
    static class Config {

        //@Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        //@Bean
        public Bean2 bean2() {
            return new Bean2();
        }

        //@Bean
        Bean3 bean3() {
            return new Bean3();
        }

        //@Bean
        Bean4 bean4() {
            return new Bean4();
        }
    }


    static class Bean1 {
        private static final Logger logger = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            logger.info("bean1 的构造方法");
        }

        @Autowired
        /**
         * Could not autowire. There is more than one bean of 'Inter' type.
         * Beans:
         * bean3   (TestBeanFactory.java) bean4   (TestBeanFactory.java)
         */
        //两个类型的 Inter  无法根据类型自动注入
        //private Inter inter;
        // 首先咱们改成bean3  测试一下

        //如果在加一下 @Resource注解 并且name设置为 bean4  那么会输入哪个bean呢?
        //这个注入bean 由 beanPostProcessor 的执行顺序决定
        @Resource(name = "bean4")
        private Inter bean3;

        public Inter getBean3() {
            return bean3;
        }

        public void setBean3(Inter bean3) {
            this.bean3 = bean3;
        }
    }

    static class Bean2 {
        private static final Logger logger = LoggerFactory.getLogger(Bean1.class);


        @Autowired
        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }


        public Bean2() {
            logger.info("bean2 的构造方法");
        }
    }

    interface Inter {

    }

    static class Bean3 implements Inter {

    }

    static class Bean4 implements Inter {

    }
}
