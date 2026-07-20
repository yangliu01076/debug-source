package org.example.client;

import org.example.properties.SmsProperties;

/**
 * @author duoyian
 * @date 2026/6/25
 */
public class SmsClient {
    private final SmsProperties properties;

    // 注意：这里没有加 @Component！
    // 因为如果你加了，使用者如果不配 sms 相关属性，或者包没扫描到，就会出问题。
    // 我们要把“是否实例化它”的主动权，交给第三步的配置类。
    public SmsClient(SmsProperties properties) {
        this.properties = properties;
    }

    public String send(String content) {
        return properties.getPrefix() + content + properties.getSuffix();
    }
}
