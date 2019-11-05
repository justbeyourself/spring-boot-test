package com.start.test.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.start.test.aspect.aop.PfVersion;
import com.start.test.aspect.aop.ThreadLocalAttrsUtils;
import com.start.test.dto.CommonBody;
import org.springframework.stereotype.Service;

/**
 * @description: hello
 * @author: zhanghuiyong
 * @create: 2019-08-08 14:58
 */
@Service
public class TestService {

    public String sayHello() {
        return "hello,Spring Boot.";
    }

    public <B> B test(B b) {
        CommonBody<B> commonBody = new CommonBody<>();
        commonBody.setMsgId("11111111");
        b = getB(commonBody);
        return b;
    }

    private <B> B getB(CommonBody<B> commonBody) {
        String json = "{\n" +
                "    \"body\": {\n" +
                "        \"msgId\": \"1567591022209\"\n" +
                "    },\n" +
                "    \"msgId\": \"M20190904175702000001\"\n" +
                "}";

        commonBody = JSONObject.parseObject(json, new TypeReference<CommonBody>() {
        });

        return commonBody.getBody();
    }

    @PfVersion("annotationDIY")
    public String annotationDIY() {
        System.out.println("annotationDIY running.");
        return ThreadLocalAttrsUtils.getPfVersion().name();
    }

}
