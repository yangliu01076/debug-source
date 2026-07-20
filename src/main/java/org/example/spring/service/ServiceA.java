package org.example.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duoyian
 * @date 2026/4/30
 */
//@DubboService(timeout = 3000)
//@Service
@Service
//@Primary
public class ServiceA implements TestService {

    @Resource
//    @Autowired
    private ServiceB serviceB;
}
