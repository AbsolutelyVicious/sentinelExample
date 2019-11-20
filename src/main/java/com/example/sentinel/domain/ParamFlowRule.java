package com.example.sentinel.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: Denis
 * @Description: 热点规则
 * @Date: Created in 2019-11-20 10:00
 * @Modified By:
 * @desc
 */
@Data
public class ParamFlowRule {

    /**
     * 资源名，必填
     */
    private String resource;

    /**
     * 限流模式,默认QPS(1)
     */
    private Integer grade;

    /**
     * 热点参数的索引，必填，对应 SphU.entry(xxx, args) 中的参数索引位置
     */
    private Integer paramIdx;

    /**
     * 限流阈值，必填
     */
    private Integer count;

    /**
     * 参数例外项，可以针对指定的参数值单独设置限流阈值，不受前面 count 阈值的限制。
     */
    private List paramFlowItemList;

}
