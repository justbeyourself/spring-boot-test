package com.zorro.start.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description: ${description}
 * @author: zorro
 * @create: 2022-03-09 17:47
 */
@Slf4j
@RestController
@RequestMapping("/share")
public class Test1Controller {

    @GetMapping("/{id}")
    public String test(@PathVariable String id) {
        log.info("share id:{}", id);
        return id;
    }
}
