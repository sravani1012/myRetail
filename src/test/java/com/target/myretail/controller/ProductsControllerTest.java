package com.target.myretail.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProductById() throws Exception {
        mockMvc.perform(get("/products/13860428"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testProductByIdNOResponse() throws Exception {
        mockMvc.perform(get("/products/1386042"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testGetProductName() throws Exception {
        mockMvc.perform(get("/products/product_name/13860428"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testGetProductNameNOResponse() throws Exception {
        mockMvc.perform(get("/products/product_name/1386042"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testGetProductDetails() throws Exception {
        mockMvc.perform(get("/products/product_details/13860428"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testGetProductDetailsNOResponse() throws Exception {
        mockMvc.perform(get("/products/product_details/1386042"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        String requestString = "{\"id\":57318640,\"product_name\":\"The Big Lebowski\",\"current_price\":{\"value\":25.87,\"currency_code\":\"USD\"}}";
        mockMvc.perform(put("/products/update_product_details")
                .content(requestString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect((jsonPath("$.id")).isNotEmpty())
                .andExpect((jsonPath("$.current_price.value")).value(25.87));
    }

    @Test
    public void testGetProducts() throws Exception {
        String productInputList = "{\n" +
                "\t\"products\": [13860428,6933639,92326560,22368388]\n" +
                "}";
        mockMvc.perform(post("/products/get_multiple_products")
                .content(productInputList).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect((jsonPath("$.[0].id")).value(13860428));
    }

}