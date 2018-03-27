package com.target.myretail.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(long productId) {
        super("Product Not Found For Id :" + productId);
    }
}
