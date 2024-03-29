package com.example.sentinel.service;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slotchain.StringResourceWrapper;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaosen
 * @date 2019/8/12 15:38
 * @description
 */
@Service
public class SentinelService {
    private static final Logger logger = LoggerFactory.getLogger(SentinelService.class);

    @Autowired
    private Sentinel2Service sentinel2Service;

    public String get(String s){
        try {
            return sentinel2Service.getException(s);
        }catch (Exception e){
            return "csf";
        }
//        return "正常测试";
    }

    @SentinelResource(fallback = "fallbackMethod", value = "getException")
    public String getException(String s) throws Exception{
        logger.info("测试日志");
        throw new RuntimeException("getUserById command failed");
    }

    public String fallbackMethod(String s){
        return "降级：" + s;
    }




}
