package org.example.config;

import org.example.client.SmsClient;
import org.example.properties.SmsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author duoyian
 * @date 2026/6/25
 */
// 1. 声明这是一个配置类
@Configuration
// 2. 开启属性配置，把 SmsProperties 注册为 Bean
@EnableConfigurationProperties(SmsProperties.class)
public class SmsAutoConfiguration {

    // 3. 注册 SmsClient Bean
    @Bean
    // 4. 核心注解：如果容器里没有 SmsClient，才用我这个默认的。
    // 这就给了使用者“覆盖默认实现”的权利（后文细讲）。
    @ConditionalOnMissingBean(SmsClient.class)
    public SmsClient smsClient(SmsProperties properties) {
        return new SmsClient(properties);
    }
}
