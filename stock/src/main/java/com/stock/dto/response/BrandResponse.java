package com.stock.dto.response;

import com.stock.domain.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BrandResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime registrationDate;

    public BrandResponse(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.code = brand.getCode();
        this.description = brand.getDescription();
        this.registrationDate = brand.getRegistrationDate();
    }
}
