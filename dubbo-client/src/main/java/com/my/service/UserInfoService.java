package com.my.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author duoyian
 * @date 2026/7/23
 */
public interface UserInfoService {

    public String getUserInfo(String name);
}
