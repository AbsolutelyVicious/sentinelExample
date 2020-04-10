package com.example.sentinel.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Denis
 * @Description:
 * @Date: Created in 2019-11-20 18:47
 * @Modified By:
 * @desc
 */
@Data
@Builder
public class User {

    private Integer userId;

    private String name;

    private Integer age;

    private String password;

}
