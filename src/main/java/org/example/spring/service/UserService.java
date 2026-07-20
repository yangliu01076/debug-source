package org.example.spring.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author duoyian
 * @date 2026/4/23
 */
@Service
public class UserService implements BeanNameAware, InitializingBean, DisposableBean {
    public void sayHello() {
        System.out.println("Hello Spring!");
        System.out.println(testService);
        System.out.println(service);
    }

    @Autowired
    private Map<String, TestService> testService;

//    @Resource
    @Autowired
    private TestService service;


    @PostConstruct
    public void myInit() {
        System.out.println("4. @PostConstruct");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("2. BeanNameAware");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("5. InitializingBean");
    }

    @PreDestroy
    public void myDestroy() {
        System.out.println("销毁: @PreDestroy");
    }

    @Override
    public void destroy() {
        System.out.println("销毁: DisposableBean");
    }
}
