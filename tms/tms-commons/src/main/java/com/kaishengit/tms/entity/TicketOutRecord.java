package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class TicketOutRecord implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;



    public static final String PAID = "已支付";

    public static final String NOT_PATD = "未支付";

    public String getOutAccountName() {
        return outAccountName;
    }

    public void setOutAccountName(String outAccountName) {
        this.outAccountName = outAccountName;
    }

    /**
     * 下发人

     */
    private String outAccountName;

    /**
     * 财务收款人
     */
    private String financeAccountName;

    /**
     * 状态（未支付或已支付）
     */
    private String state;

    /**
     * 内容
     */
    private String content;

    /**
     * 起始票号
     */
    private String biginTicketNum;

    private String endTicketNum;

    private Integer totalNum;

    /**
     * 单张票价
     */
    private BigDecimal price;

    private BigDecimal totalPrice;

    /**
     * 下发的售票名称
     */
    private String storeAccountName;

    /**
     * 下发售票id
     */
   private Integer storeAccountId;

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    /**
     * 下发人id
     */
    private Integer outAccountId;

    /**
     * 收款人id
     */
    private String financeAccountId;

    @Override
    public String toString() {
        return "TicketOutRecord{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", outAccountName='" + outAccountName + '\'' +
                ", financeAccountName='" + financeAccountName + '\'' +
                ", state='" + state + '\'' +
                ", content='" + content + '\'' +
                ", biginTicketNum='" + biginTicketNum + '\'' +
                ", endTicketNum='" + endTicketNum + '\'' +
                ", totalNum=" + totalNum +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", storeAccountName='" + storeAccountName + '\'' +
                ", storeAccountId='" + storeAccountId + '\'' +
                ", outAccountId=" + outAccountId +
                ", financeAccountId='" + financeAccountId + '\'' +
                ", payType='" + payType + '\'' +
                '}';
    }

    /**
     * 支付方式（支付宝，现金，银行卡）
     */
    private String payType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFinanceAccountName() {
        return financeAccountName;
    }

    public void setFinanceAccountName(String financeAccountName) {
        this.financeAccountName = financeAccountName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBiginTicketNum() {
        return biginTicketNum;
    }

    public void setBiginTicketNum(String biginTicketNum) {
        this.biginTicketNum = biginTicketNum;
    }

    public String getEndTicketNum() {
        return endTicketNum;
    }

    public void setEndTicketNum(String endTicketNum) {
        this.endTicketNum = endTicketNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStoreAccountName() {
        return storeAccountName;
    }

    public void setStoreAccountName(String storeAccountName) {
        this.storeAccountName = storeAccountName;
    }



    public Integer getOutAccountId() {
        return outAccountId;
    }

    public void setOutAccountId(Integer outAccountId) {
        this.outAccountId = outAccountId;
    }

    public String getFinanceAccountId() {
        return financeAccountId;
    }

    public void setFinanceAccountId(String financeAccountId) {
        this.financeAccountId = financeAccountId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}