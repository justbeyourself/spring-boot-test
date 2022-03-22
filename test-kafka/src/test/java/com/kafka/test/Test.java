package com.kafka.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kafka.test.domain.MemberInfo;
import com.kafka.test.domain.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.UUID;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2022-03-11 17:21
 */
@RunWith(SpringRunner.class)
@Slf4j
public class Test {


    @org.junit.Test
    public void messageTest() {
        TestMessage testMessage = new TestMessage();
        testMessage.setMessageId(UUID.randomUUID().toString());
        testMessage.setOneid("10000014274909");
        testMessage.setMrchCode(1L);
        testMessage.setTenantId(1L);
        testMessage.setEventCode("test");
        testMessage.setTriggerTime(System.currentTimeMillis());

        System.out.println(JSON.toJSONString(testMessage));
    }

    public static void main(String[] args) {
        TestMessage testMessage = new TestMessage();
        testMessage.setMessageId(UUID.randomUUID().toString());
        testMessage.setOneid("10000014274909");
        testMessage.setMrchCode(1L);
        testMessage.setTenantId(1L);
        testMessage.setEventCode("test");
        testMessage.setTriggerTime(System.currentTimeMillis());

        Map<String, Object> attributeMap = Maps.newHashMap();
        attributeMap.put("one","1");
        attributeMap.put("two","2022-03-15");
        attributeMap.put("three","1");
        attributeMap.put("four","购物");
        testMessage.setAttributes(attributeMap);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPhone("1818888888");
        memberInfo.setMemberId("M0001");
        memberInfo.setMemberCard("C0001");
        memberInfo.setAppid("wx0e62a90c77a54ae7");
        memberInfo.setOpenid("or0Ls0VRcNNZES4cKG22afFPl5VA");
        memberInfo.setUnionid("ooVhI0nqNYKob_T04xMWfo2bkdfI");
        testMessage.setMemberInfo(memberInfo);

        String message = JSON.toJSONString(testMessage);
        System.out.println(message);


        String ss = "{\"attributes\":{\"four\":\"购物\",\"one\":\"1\",\"two\":\"2022-03-15\",\"three\":\"1\"},\"eventCode\":\"test\",\"memberInfo\":{\"appid\":\"wx0e62a90c77a54ae7\",\"memberCard\":\"C0001\",\"memberId\":\"M0001\",\"openid\":\"or0Ls0VRcNNZES4cKG22afFPl5VA\",\"phone\":\"1818888888\",\"unionid\":\"ooVhI0nqNYKob_T04xMWfo2bkdfI\"},\"messageId\":\"5b240279-c7a6-4d35-9708-417a4343a740\",\"mrchCode\":1,\"oneid\":\"10000014274909\",\"tenantId\":1,\"triggerTime\":1647932757474}";

        TestMessage testMessage1 = JSON.parseObject(ss,TestMessage.class);
        System.out.println(testMessage1.toString());
    }
}