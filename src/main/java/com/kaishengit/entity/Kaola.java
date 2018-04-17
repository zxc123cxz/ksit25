package com.kaishengit.entity;

import java.math.BigDecimal;

public class Kaola {

    private Integer id;
    private String productName;
    private BigDecimal price;
    private BigDecimal marketPrice;
    private String place;
    private Integer commentNum;
    private Integer typeId;

    private KaolaType kaolaType;

    public static final Integer COMMENTNUM = 999;
    public Kaola() {
    }

    public Kaola(Integer id, String productName, BigDecimal price, BigDecimal marketPrice, String place, Integer commentNum, Integer typeId) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.marketPrice = marketPrice;
        this.place = place;
        this.commentNum = commentNum;
        this.typeId = typeId;
    }

    public Kaola(String productName, BigDecimal price, BigDecimal marketPrice, String place, Integer commentNum, Integer typeId) {
        this.productName = productName;
        this.price = price;
        this.marketPrice = marketPrice;
        this.place = place;
        this.commentNum = commentNum;
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public KaolaType getKaolaType() {
        return kaolaType;
    }

    public void setKaolaType(KaolaType kaolaType) {
        this.kaolaType = kaolaType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", marketPrice=" + marketPrice +
                ", place='" + place + '\'' +
                ", commentNum=" + commentNum +
                ", typeId=" + typeId +
                '}';
    }
}
