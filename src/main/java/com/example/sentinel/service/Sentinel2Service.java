package com.example.sentinel.service;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slotchain.StringResourceWrapper;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author xiaosen
 * @date 2019/8/12 16:58
 * @description
 */
@Service
public class Sentinel2Service {
    private static final Logger logger = LoggerFactory.getLogger(Sentinel2Service.class);

    @SentinelResource(fallback = "fallbackMethod", value = "getException")
    public String getException(String s) throws Exception{
        logger.info("测试日志");
        throw new RuntimeException("getUserById command failed");
    }

    public String fallbackMethod(String s){
        logger.info("test: {}", check());
        return "降级：" + s;
    }

    private boolean check(){
        try {
            DegradeRuleManager.checkDegrade(new StringResourceWrapper("getException", EntryType.OUT), null, null, 0);
            return true;
        }catch (BlockException e){
            return false;
        }
    }

}
