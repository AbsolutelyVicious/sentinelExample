package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.example.sentinel.config.FileDataSourceInit;
import com.example.sentinel.service.SentinelRule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.sentinel.common.*;

import java.util.List;

/**
 * @Author: Denis
 * @Description:
 * @Date: Created in 2019-11-20 09:16
 * @Modified By:
 * @desc
 */
@RestController
@RequestMapping("/sentinelRule")
public class ConfigurationRuleController {

    private final SentinelRule sentinelRule;
    private final FileDataSourceInit fileDataSourceInit;

    public ConfigurationRuleController(SentinelRule sentinelRule, FileDataSourceInit fileDataSourceInit) {
        this.sentinelRule = sentinelRule;
        this.fileDataSourceInit = fileDataSourceInit;
    }

    /**
     * 新增降级规则
     */
    @ResponseBody
    @PostMapping(value = {"/insertDegradeRule"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
    public Boolean insertDegradeRule(@RequestBody DegradeRule degradeRule) {
        Boolean boo = sentinelRule.insertDegradeRule(degradeRule);
        try {
            //重新加载Sentinel规则
            fileDataSourceInit.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boo;
    }

    /**
     * 修改降级规则
     */
    @ResponseBody
    @PutMapping(value = {"/editDegradeRule"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.PUT})
    public Boolean editDegradeRule(@RequestBody DegradeRule degradeRule) {
        return sentinelRule.editDegradeRule(degradeRule);
    }

    /**
     * 删除降级规则
     */
    @ResponseBody
    @DeleteMapping(value = {"/delDegradeRule/{resource}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE})
    public Boolean delDegradeRule(@PathVariable(value = "resource") String resource) {
        return sentinelRule.delDegradeRule(CommonFile.DEGRADE_RULE + resource);
    }

    /**
     * 获取所有降级规则
     */
    @ResponseBody
    @GetMapping(value = {"/getDegradeRule"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
    public List<DegradeRule> getDegradeRule() {
        return sentinelRule.getDegradeRule();
    }

}
