package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TicketStore implements Serializable {
    private Integer id;

    /**
     * 售票地点
     */
    private String storeName;

    /**
     * 售票管理人姓名
     */
    private String storeManager;

    /**
     * 售票管理人电话
     */
    private String storeTel;

    /**
     * 售票点地址
     */
    private String storeAddress;

    /**
     * 坐标 经纬度
     */
    private String storeGeoLongltde;

    /**
     * 坐标 经纬度
     */
    private String storeGeoLatitude;

    private String storeAttachment;

    private String storeManagerAttachment;

    private Date createTime;

    private Date updateTime;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreGeoLongltde() {
        return storeGeoLongltde;
    }

    public void setStoreGeoLongltde(String storeGeoLongltde) {
        this.storeGeoLongltde = storeGeoLongltde;
    }

    public String getStoreGeoLatitude() {
        return storeGeoLatitude;
    }

    public void setStoreGeoLatitude(String storeGeoLatitude) {
        this.storeGeoLatitude = storeGeoLatitude;
    }

    public String getStoreAttachment() {
        return storeAttachment;
    }

    public void setStoreAttachment(String storeAttachment) {
        this.storeAttachment = storeAttachment;
    }

    public String getStoreManagerAttachment() {
        return storeManagerAttachment;
    }

    public void setStoreManagerAttachment(String storeManagerAttachment) {
        this.storeManagerAttachment = storeManagerAttachment;
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

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    @Override
    public String toString() {
        return "TicketStore{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", storeManager='" + storeManager + '\'' +
                ", storeTel='" + storeTel + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storeGeoLongltde='" + storeGeoLongltde + '\'' +
                ", storeGeoLatitude='" + storeGeoLatitude + '\'' +
                ", storeAttachment='" + storeAttachment + '\'' +
                ", storeManagerAttachment='" + storeManagerAttachment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", storeAccountId=" + storeAccountId +
                '}';
    }
}