package org.example;

import org.example.client.SmsClient;
import org.example.common.statemachine.demo.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author duoyian
 * @date 2026/5/22
 */
@SpringBootApplication(scanBasePackages = {"org.example"})
@MapperScan("org.example.mapper")
public class SpringBootApp {

    @Resource
    private SmsClient smsClient;

    @Resource
    private OrderService orderService;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootApp.class, args);
//        Integer[] arr = {1, 2, 3};
//        Arrays.stream(arr).forEach(System.out::println);
//        Stream.of(1, 2, 3).sorted().forEach(System.out::println);
//        Object smsClient1 = run.getBean("smsClient");
//        SmsClient smsClient = (SmsClient) smsClient1;
//        System.out.println(smsClient.send("hello"));
        run.getBean(OrderService.class).processOrder();
    }
}
