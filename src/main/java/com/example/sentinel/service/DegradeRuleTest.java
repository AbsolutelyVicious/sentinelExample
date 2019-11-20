package com.example.sentinel.service;

import com.example.sentinel.domain.User;

import java.util.List;

/**
 * @Author: Denis
 * @Description: DegradeRule熔断降级测试
 * @Date: Created in 2019-11-20 18:44
 * @Modified By:
 * @desc
 */
public interface DegradeRuleTest {

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    Boolean insertUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    Boolean editUser(User user);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    Boolean delUser(String userId);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findUsers();

}
