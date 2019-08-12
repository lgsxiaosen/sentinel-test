package com.example.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author xiaosen
 * @date 2019/8/12 15:38
 * @description
 */
@Service
public class SentinelService {
    private static final Logger logger = LoggerFactory.getLogger(SentinelService.class);


    public String get(){
        return "正常测试";
    }

    @SentinelResource(fallback = "fallbackMethod", value = "getException")
    public String getException(String s){
        logger.info("测试日志");
        throw new RuntimeException("getUserById command failed");
    }

    public String fallbackMethod(String s){
        return "降级：" + s;
    }




}
