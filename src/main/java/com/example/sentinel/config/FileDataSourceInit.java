package com.example.sentinel.config;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.property.DynamicSentinelProperty;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.example.sentinel.service.SentinelRule;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Denis
 * @Description: 拉模式读取Redis持久化规则
 * @Date: Created in 2019-11-20 16:04
 * @Modified By:
 * @desc
 */
@Component("fileDataSourceInit")
public class FileDataSourceInit implements InitFunc, InitializingBean {

    private final SentinelRule sentinelRule;

    public FileDataSourceInit(SentinelRule sentinelRule) {
        this.sentinelRule = sentinelRule;
    }

    private static SentinelProperty<List<DegradeRule>> degradeCurrentProperty = new DynamicSentinelProperty<>();

    private static SentinelProperty<List<FlowRule>> flowCurrentProperty = new DynamicSentinelProperty<>();

    private static SentinelProperty<List<ParamFlowRule>> paramFlowRuleCurrentProperty = new DynamicSentinelProperty<>();

    private static SentinelProperty<List<SystemRule>> systemRuleCurrentProperty = new DynamicSentinelProperty<>();

    @Override
    public void init() throws Exception {

        //降级规则注册
        List<DegradeRule> degradeRule = sentinelRule.getDegradeRule();
        degradeCurrentProperty.updateValue(degradeRule);
        DegradeRuleManager.register2Property(degradeCurrentProperty);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }
}
