package com.example.sentinel.service;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;

import java.util.List;

/**
 * @Author: Denis
 * @Description: Sentinel规则操作
 * @Date: Created in 2019-11-20 10:45
 * @Modified By:
 * @desc
 */
public interface SentinelRule {

    /**
     * 插入降级规则
     *
     * @param degradeRule
     * @return
     */
    Boolean insertDegradeRule(DegradeRule degradeRule);

    /**
     * 修改降级规则
     *
     * @param degradeRule
     * @return
     */
    Boolean editDegradeRule(DegradeRule degradeRule);

    /**
     * 删除降级规则
     *
     * @param degradeRule
     * @return
     */
    Boolean delDegradeRule(String degradeRule);

    /**
     * 获取所有降级规则
     *
     * @return
     */
    List<DegradeRule> getDegradeRule();

}
