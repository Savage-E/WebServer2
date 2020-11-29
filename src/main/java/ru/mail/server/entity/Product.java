package ru.mail.server.entity;

import com.fasterxml.jackson.annotation.*;


@SuppressWarnings("NotNullNullableValidation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @JsonProperty("name")

    String name;
    @JsonProperty("manufacturer")

    String manufacturer;
    @JsonProperty("amount")
    int amount;

    @JsonCreator
    public Product(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "manufacturer", required = true) String manufacturer, @JsonProperty(value = "amount", required = true) int amount) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.amount = amount;
    }

    @JsonGetter("name")

    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("manufacturer")

    public String getManufacturer() {
        return manufacturer;
    }

    @JsonSetter("manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonGetter("amount")
    public int getAmount() {
        return amount;
    }

    @JsonSetter("amount")
    public void setAmount(int amount) {
        this.amount = amount;
    }


}
