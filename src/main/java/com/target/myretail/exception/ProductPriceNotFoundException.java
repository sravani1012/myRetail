package com.target.myretail.exception;

public class ProductPriceNotFoundException extends RuntimeException {
    public ProductPriceNotFoundException(long productId) {
        super("Product Price Not Found For Id :" + productId);
    }
}
