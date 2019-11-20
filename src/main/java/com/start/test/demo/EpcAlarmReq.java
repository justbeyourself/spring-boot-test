package com.start.test.demo;

import lombok.Data;

import java.util.List;

/**
 * @description: epc告警请求
 * @author: zhanghuiyong
 * @create: 2019-11-18 14:23
 */
@Data
public class EpcAlarmReq {
    /**
     * 门禁号码
     */
    private String deviceNo;

    /**
     * epc告警列表
     */
    private List<String> epcs;

}
