package com.kaishengit.tms.entity;

import java.io.Serializable;


import java.util.Date;
import java.util.List;

/** 系统账号实体类
 * @author  刘文龙
 * @date 2018 4.12
 */

public class Account implements Serializable {
    private Integer id;

    private String accountName;

    private String accountMobile;

    private String accountPassword;

    private Date oreateTime;

    private String updateTime;

    private String accountState;

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    private List<Roles> rolesList;

    private static final long serialVersionUID = 1L;

    public static final String STATE_NORMAL = "正常";
    public static final String STATE_DISABLE = "禁用";
    public static final String STATE_LOCKING = "锁定";
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Date getOreateTime() {
        return oreateTime;
    }

    public void setOreateTime(Date oreateTime) {
        this.oreateTime = oreateTime;
    }



    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Account{" +

                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountMobile='" + accountMobile + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", oreateTime=" + oreateTime +
                ", updateTime='" + updateTime + '\'' +
                ", accountState='" + accountState + '\'' +
                '}';
    }
}