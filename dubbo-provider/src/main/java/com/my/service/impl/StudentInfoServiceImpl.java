package com.my.service.impl;

import com.my.StudentInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author duoyian
 * @date 2026/7/23
 */
@Service("studentInfoService")
@DubboService
public class StudentInfoServiceImpl implements StudentInfoService {
    public String getStudentInfo(String name) {
        return "Hello " + name;
    }
}
