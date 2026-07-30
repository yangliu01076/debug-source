package org.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author duoyian
 * @date 2026/6/25
 */
@Data
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {
    private String prefix;
    private String suffix;
}
