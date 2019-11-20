package com.example.sentinel.service.impl;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.fastjson.JSON;
import com.example.sentinel.service.SentinelRule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;

import com.example.sentinel.common.*;

/**
 * @Author: Denis
 * @Description:
 * @Date: Created in 2019-11-20 10:46
 * @Modified By:
 * @desc
 */
@Service
public class SentinelRuleImpl implements SentinelRule {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Boolean insertDegradeRule(DegradeRule degradeRule) {
        try {
            redisTemplate.opsForValue().set(CommonFile.DEGRADE_RULE + degradeRule.getResource(), JSON.toJSONString(degradeRule));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean editDegradeRule(DegradeRule degradeRule) {
        try {
            redisTemplate.opsForValue().set(CommonFile.DEGRADE_RULE + degradeRule.getResource(), degradeRule);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delDegradeRule(String degradeRule) {
        return redisTemplate.delete(degradeRule);
    }

    @Override
    public List<DegradeRule> getDegradeRule() {
        //模糊查询 获取所有包含DEGRADE_RULE的key的值
        Set<String> degradeRuleKeys = redisTemplate.keys(CommonFile.DEGRADE_RULE + "*");
        List<DegradeRule> degradeRuleList = null;
        if (degradeRuleKeys != null) {
            //批量获取数据
            List<Object> objects = redisTemplate.opsForValue().multiGet(degradeRuleKeys);
            if (objects != null) {
                degradeRuleList = JSON.parseArray(objects.toString(), DegradeRule.class);
            }
        }
        return degradeRuleList;
    }

}
