package com.zorro.start.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-03-09 17:47
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/auth")
    public ResponseEntity<String> test(@RequestParam("id") String id, @RequestParam(name = "ssoToken", required = false) String ssoToken) {
        log.info("id:{},ssoToken:{}", id, ssoToken);
        if (!id.contains("share")) {
            // 做认证
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        ResponseEntity<String> build = ResponseEntity.ok().body(id);
        return build;
    }

    @GetMapping("/test")
    public String test1() {
        log.info("test ok");
        return "test ok";
    }
}
