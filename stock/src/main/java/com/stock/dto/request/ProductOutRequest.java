package com.stock.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.domain.Product;
import com.stock.domain.ProductOut;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductOutRequest {
    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private String exitDate;

    public ProductOutRequest(@NotNull Integer quantity, String exitDate) {

        this.quantity = quantity;
        this.exitDate = exitDate;
    }
    public ProductOut toModel(Product product){
        @PastOrPresent
        LocalDateTime date = LocalDateTime.parse(exitDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return new ProductOut(product,quantity,date);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getExitDate() {
        return exitDate;
    }
}
