package com.start.test.controller;

import com.start.test.dto.ChildBody;
import com.start.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-09-05 14:29
 */
@RestController
@RequestMapping("/tt")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/test")
    public String test() {
        ChildBody parm = new ChildBody();
        parm.setMsgId("111111");
        parm = testService.test(parm);
        return parm.getMsgId();
    }
}
