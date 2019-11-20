package com.example.sentinel.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.example.sentinel.domain.User;
import com.example.sentinel.service.DegradeRuleTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Denis
 * @Description:
 * @Date: Created in 2019-11-20 18:45
 * @Modified By:
 * @desc
 */
@Slf4j
@Service
public class DegradeRuleTestImpl implements DegradeRuleTest {

    private final RedisTemplate<String, Object> redisTemplate;

    public DegradeRuleTestImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    @SentinelResource(value = "熔断与降级处理insertUser", fallback = "fallbackInsertUser")
    public Boolean insertUser(User user) {
        redisTemplate.opsForValue().set(user.getUserId().toString(), JSON.toJSONString(user));
        return true;
    }

    public Boolean fallbackInsertUser(User user) {
        log.error("insertUser异常：{}", user.toString());
        return false;
    }

    @Override
    public Boolean editUser(User user) {
        return null;
    }

    @Override
    public Boolean delUser(String userId) {
        return null;
    }

    @Override
    public List<User> findUsers() {
        return null;
    }

}
