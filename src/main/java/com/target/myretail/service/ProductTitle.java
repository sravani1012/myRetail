package com.target.myretail.service;

import com.target.myretail.exception.ProductTitleNotFoundException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductTitle {

    @Value("${product.description.url}")
    String productUrl;

    @Value("${product.description.exclusion}")
    String productUrlExclusion;

    @Autowired
    RestTemplate restTemplate;

    public static final Logger logger = LoggerFactory.getLogger(ProductTitle.class);

    public String getProductTitle(long productId) {

        String constructedUrl = productUrl + "/" + productId + "?excludes=" + productUrlExclusion;
        ResponseEntity<String> responseEntity = null;
        try {
            logger.info(constructedUrl);
            responseEntity = restTemplate.getForEntity(constructedUrl, String.class);

        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ProductTitleNotFoundException(productId);
            }
            throw new RuntimeException(exception);
        }
        JSONObject productObject = new JSONObject(responseEntity.getBody());
        return productObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description").getString("title");

    }
}
