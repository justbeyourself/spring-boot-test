package com.kafka.test.domain;

import java.io.Serializable;
import java.util.Date;

public class TestMessage implements Serializable {

    /**
     * map_growingio_event
     */
    private static final long serialVersionUID = 1L;

    /**
     * 消息序列号，唯一，离线采用数据库主键
     */
    private Long serialNumber;

    /**
     * 事件code
     */
    private String eventCode;

    /**
     * oneid
     */
    private String oneid;

    /**
     * 属性MAP
     */
    private String attributes;

    /**
     * 事件时间
     */
    private Date triggerTime;

    /**
     * 
     * @return serial_number 
     */
    public Long getSerialNumber() {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber 
     */
    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 事件code
     * @return event_code 事件code
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * 事件code
     * @param eventCode 事件code
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    /**
     * oneid
     * @return oneid oneid
     */
    public String getOneid() {
        return oneid;
    }

    /**
     * oneid
     * @param oneid oneid
     */
    public void setOneid(String oneid) {
        this.oneid = oneid == null ? null : oneid.trim();
    }

    /**
     * 属性MAP
     * @return attributes 属性MAP
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * 属性MAP
     * @param attributes 属性MAP
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes == null ? null : attributes.trim();
    }

    /**
     * 事件时间
     * @return trigger_time 事件时间
     */
    public Date getTriggerTime() {
        return triggerTime;
    }

    /**
     * 事件时间
     * @param triggerTime 事件时间
     */
    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", eventCode=").append(eventCode);
        sb.append(", oneid=").append(oneid);
        sb.append(", attributes=").append(attributes);
        sb.append(", triggerTime=").append(triggerTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}