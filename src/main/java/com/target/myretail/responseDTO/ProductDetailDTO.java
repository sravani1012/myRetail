package com.target.myretail.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetailDTO {

    @JsonProperty("id")
    private long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("current_price")
    private Price currentPrice;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductDetailDTO{" +
                "productId=" + productId +
                ", currentPrice=" + currentPrice +
                ", productName='" + productName + '\'' +
                '}';
    }
}
