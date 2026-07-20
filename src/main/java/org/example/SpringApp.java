package org.example;

import org.example.spring.demo.TestImportSelector;
import org.example.spring.service.UserService;
import org.other.A;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author duoyian
 * @date 2026/4/23
 */
//@Configuration
//@ComponentScan("org.example")
//@EnableAspectJAutoProxy
////@EnableDubbo
//@Import(TestImportSelector.class)
public class SpringApp {
    public static void main(String[] args) {
        // 这一行代码，就是整个 Spring 世界的入口
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);

        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println("当前操作系统: " + property);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        A bean = context.getBean(A.class);
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(bean);
        UserService userService = context.getBean(UserService.class);
        userService.sayHello();
        int a = 1, b;
        System.out.println("真实类型: " + userService.getClass().getName());
    }
}
