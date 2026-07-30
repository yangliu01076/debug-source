package org.example.config;

import org.example.properties.HelloProperties;
import org.example.spring.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author duoyian
 * @date 2026/6/17
 */
@Configuration // 1. 标记这是一个配置类
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HelloService.class) // 4. 只有当容器中没有 HelloService 时，才创建（允许用户覆盖）
    public HelloService helloService(HelloProperties helloProperties) {
        HelloService service = new HelloService();
        service.setName(helloProperties.getName()); // 将 yml 里的属性注入
        service.setAge(helloProperties.getAge());
        service.setAddress(helloProperties.getAddress());
        return service;
    }
}
