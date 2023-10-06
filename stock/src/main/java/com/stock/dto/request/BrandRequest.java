package com.stock.dto.request;

import com.stock.domain.Brand;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BrandRequest {
    private String name;
    private String code;
    private String description;
    private LocalDateTime registrationDate = LocalDateTime.now();

    public BrandRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Brand toModel() {
        Brand brand = new Brand();
        brand.setName(name);
        brand.setCode(code);
        brand.setDescription(description);
        brand.setRegistrationDate(registrationDate);
        return brand;
    }

    public Brand toModel(Brand brand) {
        if (name != null) brand.setName(name);
        if (code != null) brand.setCode(code);
        if (description != null) brand.setDescription(description);
        return brand;
    }
}
