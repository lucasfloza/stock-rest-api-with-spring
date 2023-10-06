package com.stock.controllers;

import com.stock.domain.Category;
import com.stock.dto.request.CategoryRequest;
import com.stock.dto.response.CategoryResponse;
import com.stock.services.CategoryService;
import com.stock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping(value = "/{idOrCode}")
    public ResponseEntity<?> get(@PathVariable String idOrCode) {
        Category category = categoryService.findByIdOrCode(idOrCode);
        return ResponseEntity.status(HttpStatus.OK).body(new CategoryResponse(category));
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> allCategories = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCategories.stream().map(CategoryResponse::new).toList());
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest, UriComponentsBuilder uriBuilder) {
        Category category = categoryService.create(categoryRequest);
        URI uri = uriBuilder.path("/category").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new CategoryResponse(category));
    }

    @PutMapping(value = "/{idOrCode}")
    public ResponseEntity<?> updateCategory(@PathVariable String idOrCode, @RequestBody CategoryRequest categoryRequest, UriComponentsBuilder uriBuilder) {
        Category category = categoryService.update(idOrCode, categoryRequest);
        productService.updateProductsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(new CategoryResponse(category));
    }

    @DeleteMapping(value = "/{idOrCode}")
    public ResponseEntity<?> deleteCategoy(@PathVariable String idOrCode) {
        categoryService.delete(idOrCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
