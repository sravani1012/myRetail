package com.target.myretail.exception;

public class ProductTitleNotFoundException extends RuntimeException {

    public ProductTitleNotFoundException(long productId) {
        super("Product Title Not Available For Product Id:" + productId);
    }
}
