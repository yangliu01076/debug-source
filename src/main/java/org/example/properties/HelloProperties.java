package org.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author duoyian
 * @date 2026/6/17
 */
@Data
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String name;
    private Integer age;
    private Address address;

    @Data
    public static class Address {
        private String province;
        private String city;
        private String street;
    }
}
