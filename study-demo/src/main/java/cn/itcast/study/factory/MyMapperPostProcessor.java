package cn.itcast.study.factory;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/31 0031 21:57
 * @description： 这是描述
 * @modified By：
 */
public class MyMapperPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        // 扫描包下的资源
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = new Resource[0];
        try {
            resources = pathMatchingResourcePatternResolver.getResources("classpath:cn/itcast/study/mapper/**/*.class");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
        for (Resource resource : resources) {
            MetadataReader metadataReader = null;
            try {
                metadataReader = factory.getMetadataReader(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            //如果是接口
            if (classMetadata.isInterface()) {

                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(MapperFactoryBean.class)
                        //这里需要一个mapper类的参数
                        .addConstructorArgValue(classMetadata.getClassName())
                        //设置 SqlSessionFactory
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();

                // 这样生成的名字可用吗?  答案是不可用
                //String beanName = generator.generateBeanName(beanDefinition, beanDefinitionRegistry);

                //Spring 的源码是怎么做的?  根据接口生成beanName  只是为了生成名字
                AbstractBeanDefinition bd2 = BeanDefinitionBuilder.genericBeanDefinition(classMetadata.getClassName()).getBeanDefinition();
                String beanName = generator.generateBeanName(bd2, beanDefinitionRegistry);
                //beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
                beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);

            }

        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
