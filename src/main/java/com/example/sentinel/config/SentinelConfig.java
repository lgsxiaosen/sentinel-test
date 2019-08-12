package com.example.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author xiaosen
 * @date 2019/8/12 15:45
 * @description
 */
@Configuration
public class SentinelConfig {

    @Bean
    public void initSentinel(){
        // 资源名称
        DegradeRule rule = new DegradeRule("getException")
                // 异常比率模式
                .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT)
                // 异常比率阈值(50%)
                .setCount(3)
                // 熔断降级时间(10s)
                .setTimeWindow(10);
        DegradeRuleManager.loadRules(Collections.singletonList(rule));
    }

}
