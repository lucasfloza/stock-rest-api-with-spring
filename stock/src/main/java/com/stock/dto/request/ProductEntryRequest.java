package com.stock.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.domain.Product;
import com.stock.domain.ProductEntry;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductEntryRequest {
    @NotNull
    @Positive
    private Integer quantity;
    @NotNull
    @PositiveOrZero
    private Double  unitPrice;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private String entryDate;

    public ProductEntryRequest(Integer quantity, Double unitPrice, String entryDate) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.entryDate = entryDate;
    }

    public ProductEntry toModel(Product product){
        @PastOrPresent
        LocalDateTime date = LocalDateTime.parse(entryDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return new ProductEntry(product,date,unitPrice,quantity);
    }
}
