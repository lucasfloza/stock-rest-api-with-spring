package com.stock.dto.request;

import com.stock.domain.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryRequest {
    private String name;
    private String code;
    private String description;
    private LocalDateTime registrationDate = LocalDateTime.now();

    public CategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category toModel() {
        Category category = new Category();
        category.setName(name);
        category.setCode(code);
        category.setDescription(description);
        category.setRegistrationDate(registrationDate);
        return category;
    }

    public Category toModel(Category category) {
        if (name != null) category.setName(name);
        if (code != null) category.setCode(code);
        if (description != null) category.setDescription(description);
        return category;
    }

}
