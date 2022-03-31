package cn.itcast.feignClient.a05.factory;

import cn.itcast.feignClient.a05.Config;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/29 0029 22:28
 * @description： 这是描述
 * @modified By：
 */
public class ComponentBeanFactory implements BeanFactoryPostProcessor {


    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        //  AnnotationUtils  会判断某个类是否加了 ComponentScan 注解
        ComponentScan annotation = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (annotation != null) {
            String[] strings = annotation.basePackages();

            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
            for (String backAge : strings) {
                System.out.println(backAge);
                String path = "classpath*:" + backAge.replace(".", "/") + "/**/*.class";
                System.out.println(path);
                //Resource[] resources = context.getResources(path);
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                for (Resource resource : resources) {
                    System.out.println(resource);
                    MetadataReader metadataReader = factory.getMetadataReader(resource);
                    System.out.println("全类名:" + metadataReader.getAnnotationMetadata().getClassName());
                    System.out.println("是否加了@Component 注解" + metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
                    System.out.println("是否加了@Component 派生注解:" + metadataReader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));

                    //如果这个类有 @Component 或者派生注解
                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())
                            || metadataReader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {

                        // 生成 beanDefinition
                        AbstractBeanDefinition beanDefinition =
                                BeanDefinitionBuilder.genericBeanDefinition(metadataReader.getClassMetadata().getClassName()).getBeanDefinition();
                        //获取 工厂
                        //DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
                        if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;

                            //生成 beanNames
                            String beanName = generator.generateBeanName(beanDefinition, beanFactory);
                            System.out.println("beanName:" + beanName);
                            //注册 bean
                            beanFactory.registerSingleton(beanName, beanDefinition);
                        }
                    }
                }
            }
        }
    }
}
