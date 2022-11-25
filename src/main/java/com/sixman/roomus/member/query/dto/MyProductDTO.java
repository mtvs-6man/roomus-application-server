package com.sixman.roomus.member.query.dto;

import com.sixman.roomus.product.command.domain.model.Product;

public class MyProductDTO {

    private int productNo;
    private String category;
    private String fileUrl;
    private String funitureName;
    private int productPrice;

    public MyProductDTO() {
        super();
    }

    public MyProductDTO(int productNo, String category, String fileUrl, String funitureName, int productPrice) {
        this.productNo = productNo;
        this.category = category;
        this.fileUrl = fileUrl;
        this.funitureName = funitureName;
        this.productPrice = productPrice;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFunitureName() {
        return funitureName;
    }

    public void setFunitureName(String funitureName) {
        this.funitureName = funitureName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "MyProductDTO{" +
                "productNo=" + productNo +
                ", category='" + category + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", funitureName='" + funitureName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
