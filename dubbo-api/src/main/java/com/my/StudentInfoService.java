package com.my;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author duoyian
 * @date 2026/7/23
 */
public interface StudentInfoService {
    public String getStudentInfo(String name);
}
