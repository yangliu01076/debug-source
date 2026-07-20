package org.example.controller;

import org.example.controller.request.DebugRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author duoyian
 * @date 2026/6/25
 */
@RestController
@RequestMapping("/debug")
public class DebugController {

    @PostMapping("/hello")
    public String hello(@Valid @RequestBody DebugRequest request) {
        return "hello" + request.getName() + request.getAge() + "\n";
    }
}
