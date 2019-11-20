package com.example.sentinel.domain;

import lombok.Data;

/**
 * @Author: Denis
 * @Description: 流控规则
 * @Date: Created in 2019-11-20 09:25
 * @Modified By:
 * @desc
 */
@Data
public class FlowRule {

    /**
     * 资源名，即限流规则的作用对象
     */
    private String resource;

    /**
     * 流控针对的调用来源，若为 default 则不区分调用来源
     */
    private String limitApp;

    /**
     * 限流阈值类型（QPS 或并发线程数）；0代表根据并发数量来限流，1代表根据QPS来进行流量控制，默认：QPS 模式
     */
    private Integer grade;

    /**
     * 限流阈值
     */
    private Integer count;

    /**
     * 调用关系限流策略
     */
    private Integer strategy;

    /**
     * 流量控制效果（直接拒绝、Warm Up、匀速排队），默认：拒绝
     */
    private Integer controlBehavior;

    /**
     * 是否为集群模式
     */
    private Boolean clusterMode;

}
