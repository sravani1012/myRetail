package com.target.myretail.dao;

import com.mongodb.client.result.UpdateResult;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.exception.ProductPriceNotFoundException;
import com.target.myretail.responseDTO.Product;
import com.target.myretail.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    public Product getProduct(long productId) {
        Criteria criteria = Criteria.where("id").is(productId);
        Product product = mongoTemplate.findOne(Query.query(criteria), Product.class);
        if (StringUtils.isEmpty(product)) {
            logger.error("No product found with product Id :" + productId);
            throw new ProductNotFoundException(productId);
        }
        return product;
    }

    public List<Product> getProducts(List<Long> productId) {
        Criteria criteria = Criteria.where("id").in(productId);
        List<Product> products = mongoTemplate.find(Query.query(criteria), Product.class);
        return products;
    }

    public Product updateProductDetails(Product product) {
        Criteria criteria = Criteria.where("id").is(product.getId());
        Query query = new Query(criteria);
        Update update = new Update();
        if (product.getCurrentPrice() == null) {
            throw new ProductPriceNotFoundException(product.getId());
        }
        update.set("current_price.value", product.getCurrentPrice().getPrice());
        update.set("current_price.currency_code", product.getCurrentPrice().getCurrencyCode());
        mongoTemplate.updateMulti(query, update, Product.class);
        return product;
    }

}
