package com.target.myretail.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

public class Price {

    @NotNull
    @JsonProperty("value")
    @Field("value")
    private double price;

    @NotNull
    @JsonProperty("currency_code")
    @Field("currency_code")
    private String currencyCode;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}