package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Tickets implements Serializable {
    private Integer id;

    private String ticketNum;

    /**
     * 入库时间
     */
    private Date ticketInTime;

    /**
     * 年票状态
     */
    private String ticketState;

    private Date createTime;

    private Date updateTime;

    /**
     * 年票出库(下发时间)
     */
    private String ticketOutTime;

    /**
     * 售票点id
     */
    private Integer storeAccountId;

    /**
     * 有效期起始时间
     */
    private Date ticketValidityStart;

    /**
     * 有效期截至时间
     */
    private Date ticketVilidityEnd;

    /**
     * 顾客id
     */
    private Long customerId;

    private static final long serialVersionUID = 1L;

    public static final String TICKET_STATE_IN_STORE = "已入库";
    public static final String TICKET_STATE_OUT_STORE = "已下发";
    public static final String TICKET_STATE_SALE = "已销售";
    public static final String TICKET_STATE_LOST = "已挂失";
    public static final String TICKET_STATE_OUT_DATE = "已过期";


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Date getTicketInTime() {
        return ticketInTime;
    }

    public void setTicketInTime(Date ticketInTime) {
        this.ticketInTime = ticketInTime;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTicketOutTime() {
        return ticketOutTime;
    }

    public void setTicketOutTime(String ticketOutTime) {
        this.ticketOutTime = ticketOutTime;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Date getTicketValidityStart() {
        return ticketValidityStart;
    }

    public void setTicketValidityStart(Date ticketValidityStart) {
        this.ticketValidityStart = ticketValidityStart;
    }

    public Date getTicketVilidityEnd() {
        return ticketVilidityEnd;
    }

    public void setTicketVilidityEnd(Date ticketVilidityEnd) {
        this.ticketVilidityEnd = ticketVilidityEnd;
    }

    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", ticketNum='" + ticketNum + '\'' +
                ", ticketInTime=" + ticketInTime +
                ", ticketState='" + ticketState + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ticketOutTime='" + ticketOutTime + '\'' +
                ", storeAccountId=" + storeAccountId +
                ", ticketValidityStart=" + ticketValidityStart +
                ", ticketVilidityEnd=" + ticketVilidityEnd +
                ", customerId=" + customerId +
                '}';
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}