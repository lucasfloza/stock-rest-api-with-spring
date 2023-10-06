package com.stock.dto.request;

import com.stock.domain.Brand;
import com.stock.domain.Category;
import com.stock.domain.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductRequest {
    private String name;
    private String code;
    private String description;
    private String brandCode;
    private String categoryCode;
    private LocalDateTime registrationDate = LocalDateTime.now();

    public ProductRequest(String name, String description, String brandCode, String categoryCode) {
        this.name = name;
        this.description = description;
        this.brandCode = brandCode;
        this.categoryCode = categoryCode;
    }

    public Product toModel(Brand brand, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setCode(code);
        product.setDescription(description);
        product.setRegistrationDate(registrationDate);
        product.setBrandCode(brand);
        product.setCategoryCode(category);
        return product;
    }

    public Product toModel(Product product, Brand brand, Category category) {
        if (name != null) product.setName(name);
        if (code != null) product.setCode(code);
        if (description != null) product.setDescription(description);
        if(brandCode != null) product.setBrandCode(brand);
        if(categoryCode != null) product.setCategoryCode(category);
//        product.setDateUpdate(LocalDateTime.now());
        return product;
    }
}
