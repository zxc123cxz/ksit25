package com.kaisheng.entity;

public class Order {
    private  int id;
    private  int mid;
    private  String otime;
    private  String address;
    private  String name;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", mid=" + mid +
                ", otime='" + otime + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private  String state;
    private  String price;
}
