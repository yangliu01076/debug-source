package org.example.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duoyian
 * @date 2026/4/30
 */

@Service
@Primary
public class ServiceB implements TestService {
    @Resource
    @Lazy
//    @Autowired
    private ServiceA serviceA;
}
