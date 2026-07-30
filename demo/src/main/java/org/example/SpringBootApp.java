package org.example;

import org.example.client.SmsClient;
import org.example.common.statemachine.demo.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author duoyian
 * @date 2026/5/22
 */
@SpringBootApplication(scanBasePackages = {"org.example"})
@Lazy
@MapperScan("org.example.mapper")
public class SpringBootApp {

    @Resource
    private SmsClient smsClient;

    @Resource
    private OrderService orderService;

    public static void main(String[] args) {
//        ConfigurableApplicationContext run = SpringApplication.run(SpringBootApp.class, args);
        SpringApplication springApplication = new SpringApplication(SpringBootApp.class);
        springApplication.setRegisterShutdownHook(false); //不让 SpringBoot 自动接管关闭逻辑
        ConfigurableApplicationContext run = null;
        try {
            run = springApplication.run(args);
            final ConfigurableApplicationContext ctx = run;
            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("关闭 Spring 容器");
                        ctx.close();
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Integer[] arr = {1, 2, 3};
//        Arrays.stream(arr).forEach(System.out::println);
//        Stream.of(1, 2, 3).sorted().forEach(System.out::println);
//        Object smsClient1 = run.getBean("smsClient");
//        SmsClient smsClient = (SmsClient) smsClient1;
//        System.out.println(smsClient.send("hello"));
        run.getBean(OrderService.class).processOrder();
    }
}
