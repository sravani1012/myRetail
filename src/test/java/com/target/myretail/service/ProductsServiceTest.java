package com.target.myretail.service;

import com.target.myretail.dao.ProductDao;
import com.target.myretail.requestDTO.ProductsInputList;
import com.target.myretail.responseDTO.Price;
import com.target.myretail.responseDTO.Product;
import com.target.myretail.util.SplitAndReturn;
import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductsServiceTest {
    ProductsService productsService = new ProductsService();
    ProductDao productDao = mock(ProductDao.class);
    SplitAndReturn splitAndReturn = new SplitAndReturn();

    @Before
    public void SetUp() {
        productsService.productDao = productDao;
        productsService.splitAndReturn = splitAndReturn;
        productsService.sublistSize = 2;
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.initialize();
        productsService.productTaskExecutor = taskExecutor;
    }

    @Test
    public void testGetProducts() {
        List<Product> productsList = new ArrayList<>();
        Product product = new Product();
        Price price = new Price();
        product.setId(1001);
        product.setDescription("My Description");
        price.setPrice(10.0);
        price.setCurrencyCode("INR");
        product.setCurrentPrice(price);
        productsList.add(product);

        Product product1 = new Product();
        Price price1 = new Price();
        product1.setId(1002);
        product1.setDescription("My Description 2");
        price1.setPrice(12.0);
        price1.setCurrencyCode("USD");
        product1.setCurrentPrice(price);
        productsList.add(product1);

        ProductsInputList productsInputList = new ProductsInputList();
        productsInputList.setProductsinputlist(new ArrayList<>(Arrays.asList(1L, 2L)));
        when(productDao.getProducts(any())).thenReturn(productsList);
        List<Product> response = productsService.getProducts(productsInputList);
        assertTrue(productsList.get(0).getCurrentPrice().getPrice() == response.get(0).getCurrentPrice().getPrice());
    }
}
