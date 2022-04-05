package cn.itcast.study.A07Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 12:48
 * @description： 这是描述
 * @modified By：
 */
@SpringBootApplication
public class A07Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A07Application.class);
        context.close();
    }


    @Bean(initMethod = "init")
    public Bean1 bean1() {
        return new Bean1();
    }


    @Bean(destroyMethod = "destroy2")
    public Bean2 bean2() {
        return new Bean2();

    }
}
