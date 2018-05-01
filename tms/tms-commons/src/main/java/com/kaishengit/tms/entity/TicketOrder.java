package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class TicketOrder implements Serializable {
    private Integer id;

    /**
     * 流水号
     */
    private String ticketOrderNum;

    private BigDecimal ticketOrderPrice;

    private Date createTime;

    private Date updateTime;

    /**
     * 票号id
     */
    private Integer ticketId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 售票点
     */
    private Integer storeAccountId;

    /**
     * 典型 新办 续费 补卡
     */
    private String ticketOrderType;

    private static final long serialVersionUID = 1L;

    public static final String ORDER_TYPE_NEW = "新办订单";
    public static final String ORDER_TYPE_RENEW = "续费订单";
    public static final String ORDER_TYPE_REPLACE = "补办订单";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketOrderNum() {
        return ticketOrderNum;
    }

    public void setTicketOrderNum(String ticketOrderNum) {
        this.ticketOrderNum = ticketOrderNum;
    }

    public BigDecimal getTicketOrderPrice() {
        return ticketOrderPrice;
    }

    public void setTicketOrderPrice(BigDecimal ticketOrderPrice) {
        this.ticketOrderPrice = ticketOrderPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public String getTicketOrderType() {
        return ticketOrderType;
    }

    public void setTicketOrderType(String ticketOrderType) {
        this.ticketOrderType = ticketOrderType;
    }
}