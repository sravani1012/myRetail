package com.target.myretail.service;

import com.target.myretail.dao.ProductDao;
import com.target.myretail.responseDTO.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProduct {
    @Autowired
    ProductDao productDao;

    public static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

    public Product update(Product product) {
        return productDao.updateProductDetails(product);
    }
}
