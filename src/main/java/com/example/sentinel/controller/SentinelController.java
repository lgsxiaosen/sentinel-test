package com.example.sentinel.controller;

import com.example.sentinel.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaosen
 * @date 2019/8/12 15:42
 * @description
 */
@RestController
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    @GetMapping("/sent")
    public String get(){
        return sentinelService.getException("hello world");
    }

}
