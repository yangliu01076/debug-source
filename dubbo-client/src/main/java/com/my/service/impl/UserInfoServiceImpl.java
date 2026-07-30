package com.my.service.impl;

import com.my.StudentInfoService;
import com.my.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author duoyian
 * @date 2026/7/23
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @DubboReference(url = "dubbo://127.0.0.1:20880")
    private StudentInfoService studentInfoService;

    public String getUserInfo(String name) {
        return studentInfoService.getStudentInfo(name);
    }
}
