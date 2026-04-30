package org.example.spring.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duoyian
 * @date 2026/4/30
 */
@Service
public class ServiceA {

    @Resource
    private ServiceB serviceB;
}
