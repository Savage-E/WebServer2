package ru.mail.server.entity;

import com.fasterxml.jackson.annotation.*;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NotNullNullableValidation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @JsonProperty("name")
    @NotNull
    String name;
    @JsonProperty("manufacturer")
    @NotNull
    String manufacturer;
    @JsonProperty("amount")
    int amount;

    @JsonCreator
    public Product(@JsonProperty(value = "name", required = true) @NotNull String name, @JsonProperty(value = "manufacturer", required = true) @NotNull String manufacturer, @JsonProperty(value = "anount", required = true) int amount) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.amount = amount;
    }

    @JsonGetter("name")
    @NotNull
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(@NotNull String name) {
        this.name = name;
    }

    @JsonGetter("manufacturer")
    @NotNull
    public String getManufacturer() {
        return manufacturer;
    }

    @JsonSetter("manufacturer")
    public void setManufacturer(@NotNull String manufacturer) {
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
