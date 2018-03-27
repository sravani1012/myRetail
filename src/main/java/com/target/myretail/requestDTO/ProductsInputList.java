package com.target.myretail.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProductsInputList {
    @JsonProperty("products")
    List<Long> productsinputlist = new ArrayList<>();

    public List<Long> getProductsinputlist() {
        return productsinputlist;
    }

    public void setProductsinputlist(List<Long> productsinputlist) {
        this.productsinputlist = productsinputlist;
    }

    @Override
    public String toString() {
        return "ProductsInputList{" +
                "productsinputlist=" + productsinputlist +
                '}';
    }
}
