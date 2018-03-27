package com.target.myretail.controller;

import com.target.myretail.requestDTO.ProductsInputList;
import com.target.myretail.responseDTO.Product;
import com.target.myretail.responseDTO.ProductDetailDTO;
import com.target.myretail.service.ProductDetail;
import com.target.myretail.service.ProductTitle;
import com.target.myretail.service.ProductsService;
import com.target.myretail.service.UpdateProduct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;
    @Autowired
    ProductTitle productTitle;
    @Autowired
    ProductDetail productDetail;
    @Autowired
    UpdateProduct updateProduct;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductId(@PathVariable("id") long id) {
        return productsService.getProduct(id);
    }

    @RequestMapping(value = "/product_name/{id}", method = RequestMethod.GET)
    public String getProductName(@PathVariable("id") long id) {
        return productTitle.getProductTitle(id);
    }

    @RequestMapping(value = "/product_details/{id}", method = RequestMethod.GET)
    public ProductDetailDTO getProductDetails(@PathVariable("id") long id) {
        return productDetail.getProductDetails(id);
    }

    @RequestMapping(value = "/update_product_details", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@RequestBody Product product) {
        return updateProduct.update(product);
    }

    @RequestMapping(value = "/get_multiple_products", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestBody ProductsInputList ids) {

        return productsService.getProducts(ids);
    }
}
