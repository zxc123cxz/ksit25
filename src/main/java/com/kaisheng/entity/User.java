package com.kaisheng.entity;

public class User {

    private Integer id ;
    private String publishname;
    private String address;
    private String tel;
    private String pname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublishname() {
        return publishname;
    }

    public void setPublishname(String publishname) {
        this.publishname = publishname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", publishname='" + publishname + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", pname='" + pname + '\'' +
                '}';
    }
}
