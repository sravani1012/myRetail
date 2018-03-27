package com.target.myretail.service;

import com.target.myretail.responseDTO.Product;
import com.target.myretail.responseDTO.ProductDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetail {

    @Autowired
    ProductTitle productTitle;

    @Autowired
    ProductsService productsService;

    public ProductDetailDTO getProductDetails(long id) {
        Product productDetails = productsService.getProduct(id);
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setProductId(id);
        productDetailDTO.setCurrentPrice(productDetails.getCurrentPrice());
        productDetailDTO.setProductName(productTitle.getProductTitle(id));

        return productDetailDTO;
    }
}
