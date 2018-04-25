package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StoreAccount implements Serializable {
    private Integer id;

    /**
     * 售票人账号(手机号)
     */
    private String storeAccount;

    /**
     * 售票人密码(默认手机号后六位)
     */
    private String storePassword;

    private Date createTime;

    private Date updateTime;

    /**
     * 默认正常
     */
    private String storeState;

    private static final long serialVersionUID = 1L;

    public static final String NOR_MAL = "正常";

    public static final String DISA_BLE = "禁用";

    public static final String LOCK = "锁定";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
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

    @Override
    public String toString() {
        return "StoreAccount{" +
                "id=" + id +
                ", storeAccount='" + storeAccount + '\'' +
                ", storePassword='" + storePassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", storeState='" + storeState + '\'' +
                '}';
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

}