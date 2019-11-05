package com.start.test.dto;

import lombok.Data;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-09-05 14:14
 */
@Data
public class CommonBody<T> {

    String msgId;

    T body;
}
