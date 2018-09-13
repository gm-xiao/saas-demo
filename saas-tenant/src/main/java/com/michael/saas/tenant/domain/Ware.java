package com.michael.saas.tenant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ware")
public class Ware implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "good_id", length = 32)
    private String goodId;

    @Column(name = "good_name", length = 60)
    private String goodName;

    @Column(name = "code", length = 30)
    private String code;

    @Column(name = "barcode", length = 30)
    private String barcode;

    @Column(name = "image_url", length = 125)
    private String imageUrl;

    @Column(name = "cost_price", precision=18, scale = 2)
    private BigDecimal costPrice;

    @Column(name = "sale_price", precision=18, scale = 2)
    private BigDecimal salePrice;

    @Column(name = "stock", precision=11)
    private Integer stock;

    private String attributeId;

    private String attributeName;

    @Column(name = "state", length = 4)
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
