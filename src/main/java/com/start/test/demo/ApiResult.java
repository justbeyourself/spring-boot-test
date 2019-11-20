package com.start.test.demo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class ApiResult implements Serializable {

    private Boolean success;

    private int code;

    private String errorMsg;

    private Object data;

    public ApiResult() {
        this.success = true;
    }
}
