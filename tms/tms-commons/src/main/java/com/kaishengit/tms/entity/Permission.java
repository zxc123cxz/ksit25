package com.kaishengit.tms.entity;

import jdk.internal.dynalink.beans.StaticClass;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Permission implements Serializable {
    private Integer id;

    private String permissionName;

    private String permissionCode;

    private String permissionType;

    private Integer parentId;

    private Date createTime;

    private Date updatetime;

    private String url;

    private static final long serialVersionUID = 1L;

    public static final String MENU = "菜单";
    public static final String BUTTON = "按钮";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionCode='" + permissionCode + '\'' +
                ", permissionType='" + permissionType + '\'' +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                ", updatetime=" + updatetime +
                ", url='" + url + '\'' +
                '}';
    }

    public void setUrl(String url) {
        this.url = url;
    }
}