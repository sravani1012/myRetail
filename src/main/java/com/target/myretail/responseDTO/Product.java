package com.target.myretail.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;

@Document(collection = "products")
@TypeAlias("Product")
public class Product {

    @JsonProperty("id")
    @Field("id")
    @Indexed(name = "id", unique = true)
    private long id;

    @JsonProperty("product_name")
    @Field("name")
    private String description;

    @JsonProperty("current_price")
    @Field("current_price")
    private Price currentPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }
}
