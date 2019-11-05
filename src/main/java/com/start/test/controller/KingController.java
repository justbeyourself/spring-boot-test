package com.start.test.controller;

import com.start.test.demo.king.BufferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-10-18 15:30
 */
@RestController
@RequestMapping("/king")
public class KingController {

    @Autowired
    private BufferService bufferService;

    @GetMapping("/queryAll")
    public Map queryAllBuffers() {
       return bufferService.getAll();
    }

//    @PostMapping("/queryBuffer")
//    public String queryAllBuffers(@) {
//        return bufferService.getAll();
//    }
}
