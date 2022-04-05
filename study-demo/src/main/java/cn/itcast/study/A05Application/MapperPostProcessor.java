package cn.itcast.study.A05Application;

import cn.itcast.study.config.Config;
import cn.itcast.study.factory.MyBeanPostProcessor;
import cn.itcast.study.factory.MyMapperPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/31 0031 21:38
 * @description： 这是描述
 * @modified By：
 */
public class MapperPostProcessor {

    public static void main(String[] args) throws IOException {


        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);


        //context.registerBean(ConfigurationClassPostProcessor.class);



        //咱们自己手写  @Bean 注解解析
        context.registerBean(MyBeanPostProcessor.class);

        //添加咱们自己定义 @Mapper注解处理器 就是解析Mapper 接口
        context.registerBean(MyMapperPostProcessor.class);

        context.refresh();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name :
                beanDefinitionNames) {
            System.out.println(name);

        }


        context.close();
    }
}
