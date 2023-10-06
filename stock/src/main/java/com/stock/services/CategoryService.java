package com.stock.services;

import com.stock.domain.Category;
import com.stock.dto.request.CategoryRequest;
import com.stock.exceptions.ResourceNotFoundException;
import com.stock.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByIdOrCode(String idOrCode) {
        Category category;
        try {
            Long id = Long.parseLong(idOrCode);
            category = categoryRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("No categories found with this ID!"));
        } catch (NumberFormatException e) {
            category = categoryRepository.findByCode(idOrCode).orElseThrow(() ->
                    new ResourceNotFoundException("No categories found with this CODE!"));
        }
        return category;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(CategoryRequest categoryRequest) {
        Category category = categoryRequest.toModel();
        if (category.getCode() == null) {
            int size = findAll().size();
            String numberFormated = String.format("%05d", size);
            category.setCode("C" + numberFormated);
        } else {
            verifyCode(categoryRequest.getCode());
        }

        return categoryRepository.save(category);
    }

    public Category update(String idOrCode, CategoryRequest categoryRequest) {
        verifyCode(categoryRequest.getCode());
        Category category = findByIdOrCode(idOrCode);
        return categoryRepository.save(categoryRequest.toModel(category));
    }

    public void delete(String idOrCode) {
        Category category = findByIdOrCode(idOrCode);
        try {
            categoryRepository.delete(category);
        }catch (Exception e) {
            throw new IllegalArgumentException("Try to delete a category linked to a product, please unlink it first.");
        }
    }

    private void verifyCode(String code) {
        if (categoryRepository.existsByCode(code))
            throw new ResourceNotFoundException("There is already a category registered with this code");
    }
}
