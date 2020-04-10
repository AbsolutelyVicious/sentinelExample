package com.example.sentinel.controller;

import com.example.sentinel.domain.User;
import com.example.sentinel.service.DegradeRuleTest;
import com.example.sentinel.util.MathUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author: Denis
 * @Description:
 * @Date: Created in 2019-11-20 18:40
 * @Modified By:
 * @desc
 */
@RestController
@RequestMapping("/test")
public class DegradeRuleTestController {

    private final DegradeRuleTest degradeRuleTest;

    public DegradeRuleTestController(DegradeRuleTest degradeRuleTest) {
        this.degradeRuleTest = degradeRuleTest;
    }

    /**
     * 修改降级规则
     */
    @ResponseBody
    @PostMapping(value = {"/insertUser"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
    public Boolean insertUser() {

        User user = User.builder()
                .age(21)
                .name("二狗子")
                .password(UUID.randomUUID().toString())
                .userId(MathUtil.judge(UUID.randomUUID().hashCode()))
                .build();

        return degradeRuleTest.insertUser(user);
    }

}
