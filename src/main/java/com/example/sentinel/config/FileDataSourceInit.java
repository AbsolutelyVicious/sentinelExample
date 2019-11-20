package com.example.sentinel.config;

import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.datasource.*;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.property.DynamicSentinelProperty;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.sentinel.common.CommonFile;
import com.example.sentinel.service.SentinelRule;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    private static SentinelProperty<List<DegradeRule>> currentProperty = new DynamicSentinelProperty<>();

    @Override
    public void init() throws Exception {

        //降级规则注册
        List<DegradeRule> degradeRule = sentinelRule.getDegradeRule();
        currentProperty.updateValue(degradeRule);
        DegradeRuleManager.register2Property(currentProperty);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }
}
