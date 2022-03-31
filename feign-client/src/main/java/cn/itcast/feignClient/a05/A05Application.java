package cn.itcast.feignClient.a05;


import cn.itcast.feignClient.a05.factory.ComponentBeanFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;


/**
 * @author ：dongdong
 * @date ：Created in 2022/3/29 0029 20:22
 * @description： 这是描述
 * @modified By：
 */
public class A05Application {

    public static void main(String[] args) throws IOException {

        //这是一个 "干净的容器"
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);
        //加了这个  beanFactory 后置处理器  会扫描  @Bean  @Component  @Import  @ImportResource
        //context.registerBean(ConfigurationClassPostProcessor.class);

        //如果加  扫描 @Mapper 注解
/*        context.registerBean(MapperScannerConfigurer.class, beanDefinition -> {
            beanDefinition.getPropertyValues().add("basePackage", "cn.itcast.feignClient.a05.mapper");
            //此时会多打印一个bean   mapper1
        });*/


        // 使用咱们抽取的方法  也就是咱们自己写的 component注解的 后置处理器
        //context.registerBean(ComponentBeanFactory.class);

        //@Bean 如何被处理呢?
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader = factory.getMetadataReader(new ClassPathResource("cn/itcast/feignClient/a05/Config.class"));
        Set<MethodMetadata> annotatedMethods =
                metadataReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata methodMetadata : annotatedMethods) {
            System.out.println(methodMetadata);

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            builder.setFactoryMethodOnBean(methodMetadata.getMethodName(), "config");
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            context.getDefaultListableBeanFactory().registerSingleton(
                    methodMetadata.getMethodName(), beanDefinition
            );
        }
        /**
         * org.springframework.context.annotation.ConfigurationClassPostProcessor
         * bean1
         * sqlSessionFactoryBean
         * dataSource   这些就会被打印出来
         *
         * 这个BeanFactory  后置处理会 扫描  @Bean  @Component  @Import  @ImportResource
         */
        //==========================================

        //初始化容器
        context.refresh();

        //查看有多少 bean
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
            // 只有 config  我们自己手动注入的bean 打印了出来
            //那么 @Bean  @component  这些bean 谁来负责注入的呢?
        }

        //关闭容器
        context.close();

    }
}
