package com.stock.dto.response;

import com.stock.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String code;
    private LocalDateTime registrationDate;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.registrationDate = category.getRegistrationDate();
    }
}
