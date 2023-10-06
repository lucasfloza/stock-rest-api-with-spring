package com.stock.dto.response;

import com.stock.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String code;
    private LocalDateTime registrationDate;
    private LocalDateTime lastUpdateDate;
    private BigDecimal unitPrice;
    private String brandCode;
    private String categoryCode;
    private Integer quantity;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.code = product.getCode();
        this.registrationDate = product.getRegistrationDate();
        this.lastUpdateDate = product.getDateUpdate() == null ? product.getRegistrationDate() : product.getDateUpdate();
        this.unitPrice = product.getUnitPrice();
        this.brandCode = product.getBrandCode() != null ? product.getBrandCode().getCode(): null;
        this.categoryCode =  product.getCategoryCode() != null ?product.getCategoryCode().getCode(): null;
        this.quantity = product.getQuantity();
    }
}
