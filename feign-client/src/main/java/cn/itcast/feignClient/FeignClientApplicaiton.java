package cn.itcast.feignClient;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/16 0016 16:02
 * @description： 这是描述
 * @modified By：
 */

@SpringBootApplication
public class FeignClientApplicaiton {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
                SpringApplication.run(FeignClientApplicaiton.class);


/*        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");

        singletonObjects.setAccessible(true);

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        Map<String, Object> o = (Map<String, Object>) singletonObjects.get(beanFactory);


        o.entrySet().stream().filter(e -> e.getKey().startsWith("component")).forEach(e -> {
            System.out.println("key:" + e.getKey() + "===" + "value:" + e.getValue());
        });


        //国际语言
        //context.getMessage();
        //资源通配路径
        Resource resource = context.getResource("classpath:application.yml");
        System.out.println(resource);
        Resource[] resources = context.getResources("classpath*:META-INF/spring-factories");

        for (Resource resource1 : resources) {
            System.out.println(resource1);
        }

        //获取配置信息
        String port = context.getEnvironment().getProperty("server.port");
        System.out.println(port);

        String property = context.getEnvironment().getProperty("java.home");
        System.out.println(property);*/
    }
}
