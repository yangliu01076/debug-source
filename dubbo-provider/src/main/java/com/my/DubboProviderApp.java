package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author duoyian
 * @date 2026/7/23
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class // 如果引入了 JPA
})
public class DubboProviderApp {
    public static void main(String[] args) {
        System.setProperty("dubbo.protocol.host", "127.0.0.1");
        System.setProperty("dubbo.local.ip", "127.0.0.1");
        ConfigurableApplicationContext run = SpringApplication.run(DubboProviderApp.class, args);
    }
}