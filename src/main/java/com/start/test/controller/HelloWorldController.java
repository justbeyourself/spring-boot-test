package com.start.test.controller;

import com.start.test.dto.ParamDTO;
import com.start.test.dto.UserDTO;
import com.start.test.service.Test2Service;
import com.start.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-08-08 15:14
 */
@RestController
@RequestMapping("/test")
public class HelloWorldController {

    @Autowired
    TestService testService;

    @Autowired
    @Qualifier("test2")
    Test2Service test2Service;

    /**
     * hello
     *
     * @return
     */
    @GetMapping("/hello")
    public String sayHello() {
        return testService.sayHello();
    }

    /**
     * test2
     * 两种注入服务方式Qualifier
     *
     * @return
     */
    @GetMapping("/test2")
    public String test2() {
        return test2Service.test2();
    }

    /**
     * URL 支持简单表达式
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/test3", method = RequestMethod.POST)
    public String test3(@PathVariable(value = "id") String id) {
        System.out.println("[test3] " + id);
        return test2Service.test3();
    }

    /**
     * URL中可以使用${}获取系统配置或环境变量
     *
     * @return
     */
    @RequestMapping(value = "/${conf.test}/test4", method = RequestMethod.GET)
    public String test4() {
        return test2Service.test4();
    }

    /**
     * consumes指定Content-Type
     *
     * @return
     */
    @GetMapping(value = "/test5", consumes = "application/json")
    public String test5() {
        return test2Service.test5();
    }

    /**
     * produces 对应HTTP请求中Accept字段
     *
     * @return
     */
    @GetMapping(value = "/test6", produces = MediaType.ALL_VALUE)
    public String test6() {
        return test2Service.test6();
    }

    /**
     * 指定请求参数key及是否必填
     *
     * @param id
     * @param name
     * @return
     */
    @PostMapping(value = "/test7")
    public String test7(@RequestParam(value = "id") Long id, String name) {
        return "test7";
    }

    /**
     * 接收JSON消息体
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "/test8")
    public String test8(@RequestBody UserDTO userDTO) {
        return userDTO.toString();
    }

    /**
     * 传入文件
     *
     * @param name
     * @return
     */
    @PostMapping(value = "/test9")
    public String test9(String name, MultipartFile file) {
        String filePath = "/Users/zhanghuiyong/Documents/test/" + file.getOriginalFilename();
        try {
            File newFile = new File(filePath);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "test9";
    }


    /**
     * 批量传入文件
     *
     * @param name
     * @return
     */
    @PostMapping(value = "/test10")
    public String test10(String name, MultipartFile[] files) {
        Arrays.stream(files).forEach(multipartFile -> {
            String filePath = "/Users/zhanghuiyong/Documents/test/" + multipartFile.getOriginalFilename();
            try {
                File newFile = new File(filePath);
                multipartFile.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return "test10";
    }


    /**
     * @param name
     * @return
     */
    @PostMapping(value = "/test11")
    public Map test10(@RequestBody Map<String, String> name) {

        System.out.println(name);
        return name;
    }

    /**
     * @param paramDTO
     * @return
     */
    @PostMapping(value = "/test12")
    public String test10(@RequestBody ParamDTO paramDTO) {
        return paramDTO.getMsgId();
    }

    /**
     * 自定义注解和ThreadLocal test
     *
     * @return
     */
    @GetMapping(value = "/test13")
    public String test13() {
        return testService.annotationDIY();
    }
}
