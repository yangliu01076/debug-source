package org.example.spring.service;

import lombok.Data;
import org.example.properties.HelloProperties;

/**
 * @author duoyian
 * @date 2026/6/17
 */
@Data
public class HelloService {
    // 允许通过配置文件注入的名字
    private String name;

    private Integer age;

    private Address address;

    public void setAddress(HelloProperties.Address address) {
        this.address = new Address();
        this.address.setProvince(address.getProvince());
        this.address.setCity(address.getCity());
        this.address.setStreet(address.getStreet());
    }

    @Data
    public static class Address {
        private String province;
        private String city;
        private String street;
    }


    public String sayHello() {
        return "Hello, " + name + "!"+ " Your age is " + age+ "! Welcome to Spring Boot Starter!"+ " Your address is " + address.getProvince() + address.getCity() + address.getStreet() + "!";
    }
}
