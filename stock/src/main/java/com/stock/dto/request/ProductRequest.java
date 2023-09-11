package com.stock.dto.request;

import com.stock.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @PositiveOrZero
    private Integer minimumQuantity;
    @NotNull
    @Positive
    private Integer maximumQuantity;

    public ProductRequest(String name, String description, Integer minimumQuantity, Integer maximumQuantity) {
        this.name = name;
        this.description = description;
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public Integer getMaximumQuantity() {
        return maximumQuantity;
    }

    public Product toModel(){
        return new Product(name,description,minimumQuantity,maximumQuantity);
    }
}
