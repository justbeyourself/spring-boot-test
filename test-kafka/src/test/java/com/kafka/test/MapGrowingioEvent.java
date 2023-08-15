package com.kafka.test;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author chensihong
 * @since 2022-03-14
 */
public class MapGrowingioEvent implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 分片标示
     */
    private String partNumber;

    /**
     * 租户ID
     */
    private Long tenantId;


    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getOneid() {
        return oneid;
    }

    public void setOneid(String oneid) {
        this.oneid = oneid;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "MapGrowingioEvent{" +
        "serialNumber=" + serialNumber +
        ", eventCode=" + eventCode +
        ", oneid=" + oneid +
        ", attributes=" + attributes +
        ", triggerTime=" + triggerTime +
        ", partNumber=" + partNumber +
        ", tenantId=" + tenantId +
        "}";
    }
}
