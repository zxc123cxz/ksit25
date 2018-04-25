package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 
 */
public class Roles implements Serializable {
    private Integer id;

    private String rolesName;

    private String rolesCode;

    private Date createTime;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    private List<Permission> permissionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getRolesCode() {
        return rolesCode;
    }

    public void setRolesCode(String rolesCode) {
        this.rolesCode = rolesCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", rolesName='" + rolesName + '\'' +
                ", rolesCode='" + rolesCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime(Date date) {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}