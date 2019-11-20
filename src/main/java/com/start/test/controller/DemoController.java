package com.start.test.controller;

import com.alibaba.fastjson.JSON;
import com.start.test.demo.ApiResult;
import com.start.test.demo.EpcAlarmReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhanghuiyong
 * @create: 2019-11-20 17:51
 */
@RestController
@RequestMapping("/localserver/securitydoor")
public class DemoController {

    @PostMapping("/epc/alarm")
    public ApiResult alarm(@RequestBody EpcAlarmReq epcAlarmReq) {
        System.out.println(JSON.toJSONString(epcAlarmReq));
        return new ApiResult();
    }
}
