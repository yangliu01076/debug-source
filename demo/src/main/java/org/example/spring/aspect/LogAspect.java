package org.example.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author duoyian
 * @date 2026/4/23
 */
@Aspect
@Component
public class LogAspect {
    @Before("execution(* org.example.spring.service.UserService.sayHello(..))")
    public void before() {
        System.out.println("=== 切面日志 ===");
    }
}
