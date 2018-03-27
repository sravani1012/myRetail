package com.target.myretail.dao;

import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.exception.ProductPriceNotFoundException;
import com.target.myretail.responseDTO.Price;
import com.target.myretail.responseDTO.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDaoTest {
    ProductDao productDao = new ProductDao();
    MongoTemplate mongoTemplate = mock(MongoTemplate.class);
    Product product = new Product();

    @Before
    public void setUp() throws Exception {
        Price price = new Price();
        price.setCurrencyCode("Any Code");
        price.setPrice(12.34);
        product.setId(32280736);
        product.setDescription("The French Connection");
        product.setCurrentPrice(price);
        productDao.mongoTemplate = mongoTemplate;
    }

    @Test
    public void getProductTest() {
        when(mongoTemplate.findOne(any(), any())).thenReturn(product);
        Product actualproduct = productDao.getProduct(32280736);
        assertEquals("The French Connection", actualproduct.getDescription());
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductTestNotFoundException() {
        when(mongoTemplate.findOne(any(), any())).thenReturn(null);
        productDao.getProduct(32280736);
    }

    @Test
    public void updateProductDetailsTest() {
        Product actualProduct = productDao.updateProductDetails(product);
        assertTrue(12.34 == actualProduct.getCurrentPrice().getPrice());
    }

    @Test(expected = ProductPriceNotFoundException.class)
    public void updateProductDetailsTestThrowsProductPriceNotFound() {
        Product productwithEmptyCurrentPrice = new Product();
        productDao.updateProductDetails(productwithEmptyCurrentPrice);

    }
}
