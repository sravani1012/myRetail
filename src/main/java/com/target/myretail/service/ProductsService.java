package com.target.myretail.service;

import com.target.myretail.dao.ProductDao;
import com.target.myretail.requestDTO.ProductsInputList;
import com.target.myretail.responseDTO.Product;
import com.target.myretail.util.SplitAndReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    public static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

    @Autowired
    ProductDao productDao;
    @Autowired
    SplitAndReturn splitAndReturn;
    @Autowired
    TaskExecutor productTaskExecutor;
    @Value("${sublist.size}")
    int sublistSize;


    public Product getProduct(long productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProducts(ProductsInputList ids) {
        List<Product> productsList = new ArrayList<>();
        logger.info("Ids ::" + ids.getProductsinputlist().size());

        List<List<Long>> sublists = splitAndReturn.splitAndReturn(ids.getProductsinputlist(), sublistSize);
        logger.info("sublists::" + sublists.size());

        List<CompletableFuture<List<Product>>> futures = new ArrayList<>();
        sublists.stream().forEach(sublist -> {
            futures.add(CompletableFuture.supplyAsync(() -> {
                return productDao.getProducts(sublist);

            }, productTaskExecutor));

            futures.stream().forEach(future -> {
                try {
                    productsList.addAll(future.get());
                } catch (InterruptedException e) {
                    logger.debug(e.getMessage());
                    throw new RuntimeException(e.getMessage());
                } catch (ExecutionException e) {
                    logger.debug(e.getMessage());
                    throw new RuntimeException(e.getMessage());
                }
            });

        });
        return productsList;
    }

}
