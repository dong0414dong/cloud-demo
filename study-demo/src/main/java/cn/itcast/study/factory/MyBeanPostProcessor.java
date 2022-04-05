package cn.itcast.study.factory;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/31 0031 23:16
 * @description： 这是描述
 * @modified By：
 */
public class MyBeanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {


        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader = null;
        try {
            metadataReader = factory.getMetadataReader(new ClassPathResource("cn/itcast/study/config/Config.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<MethodMetadata> annotatedMethods =
                metadataReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());

        for (MethodMetadata methodMetadata : annotatedMethods) {

            System.out.println(methodMetadata);

            Map<String, Object> annotationAttributes = methodMetadata.getAnnotationAttributes(Bean.class.getName());

            String string = annotationAttributes.get("initMethod").toString();

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            builder.setFactoryMethodOnBean(methodMetadata.getMethodName(), "config");
            //这里需要对有参数的方法 执行自动注入的方式
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

            if (string.length() > 0) {
                builder.setInitMethodName(string);
            }

            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
                beanFactory.registerBeanDefinition(methodMetadata.getMethodName(), beanDefinition);

            }

        }
    }
}
