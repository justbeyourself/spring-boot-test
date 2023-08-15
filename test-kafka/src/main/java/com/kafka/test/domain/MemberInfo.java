package com.kafka.test.domain;

import lombok.Data;

/**
 * @description: 会员信息
 * @author: zorro
 * @create: 2022-03-22 14:41
 */
@Data
public class MemberInfo {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 会员Id
     */
    private String memberId;

    /**
     * 会员卡号
     */
    private String memberCard;

    /**
     * 微信appid
     */
    private String appid;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 微信unionid
     */
    private String unionid;
}
