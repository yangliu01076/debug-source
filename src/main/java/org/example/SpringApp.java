package org.example;

import org.example.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author duoyian
 * @date 2026/4/23
 */
@Configuration
@ComponentScan("org.example.spring")
@EnableAspectJAutoProxy
public class SpringApp {
    public static void main(String[] args) {
        // 这一行代码，就是整个 Spring 世界的入口
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);

        UserService userService = context.getBean(UserService.class);
        userService.sayHello();
        System.out.println("真实类型: " + userService.getClass().getName());
    }
}
