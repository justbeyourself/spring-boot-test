package com.start.test.demo.king.domain;

import lombok.Data;

/**
 * 角色
 *
 * @author: zhanghuiyong
 * @create: 2019-10-18 14:11
 */
@Data
public class Role {
    /**
     * 名字
     */
    String name;
    
    /**
     * 国家
     */
    String country;

    /**
     * 职业
     */
    String job;
}
