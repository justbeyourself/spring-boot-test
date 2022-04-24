package com.kafka.test.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class TestMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息序列号，唯一
     */
    private String messageId;

    /**
     * 商户code,需分配
     */
    private Long mrchCode;

    /**
     * 租户ID，需分配
     */
    private Long tenantId;

    /**
     * 事件code
     */
    private String eventCode;

    /**
     * oneid
     */
    private String oneid;

    /**
     * 属性MAP
     */
    private Map<String, Object> attributes;

    /**
     * 事件时间
     */
    private Long triggerTime;

    /**
     * 会员信息
     *
     * 开卡，入会等事件需携带，不带后面相关触点无法触发
     */
    private MemberInfo memberInfo;
}