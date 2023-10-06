package com.stock.controllers;

import com.stock.domain.Brand;
import com.stock.domain.Category;
import com.stock.domain.OperationType;
import com.stock.domain.Product;
import com.stock.dto.request.BrandRequest;
import com.stock.dto.request.CategoryRequest;
import com.stock.dto.request.OperationRequest;
import com.stock.dto.request.ProductRequest;
import com.stock.services.BrandService;
import com.stock.services.CategoryService;
import com.stock.services.OperationService;
import com.stock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController()
@RequestMapping("/dataMock")
public class MockData {
    @Autowired
    BrandService brandService = new BrandService();
    @Autowired
    CategoryService categoryService = new CategoryService();
    @Autowired
    ProductService productService = new ProductService();
    @Autowired
    OperationService operationService = new OperationService();

    @GetMapping
    public ResponseEntity<?> dadosMock() {

        Brand brand1 = brandService.create(new BrandRequest("Brand 1", "Brand 1"));
        Brand brand2 = brandService.create(new BrandRequest("Brand 2", "Brand 2"));
        Brand brand3 = brandService.create(new BrandRequest("Brand 3", "Brand 3"));

        Category category1 = categoryService.create(new CategoryRequest("Category 1", "Category 1"));
        Category category2 = categoryService.create(new CategoryRequest("Category 2", "Category 2"));
        Category category3 = categoryService.create(new CategoryRequest("Category 3", "Category 3"));

        Product product1 = productService.create(new ProductRequest("Product 1", "Product 1", brand1.getCode(), category1.getCode()), brand1, category1);
        Product product2 = productService.create(new ProductRequest("Product 2", "Product 2", brand2.getCode(), category2.getCode()), brand2, category2);
        Product product3 = productService.create(new ProductRequest("Product 3", "Product 3", brand3.getCode(), category3.getCode()), brand3, category3);

        operationService.create(new OperationRequest(5, BigDecimal.valueOf(54.45), OperationType.INPUT,product1.getCode()));
        operationService.create(new OperationRequest(10, BigDecimal.valueOf(1000000.45), OperationType.INPUT,product2.getCode()));
        operationService.create(new OperationRequest(8, BigDecimal.valueOf(5004.45), OperationType.INPUT,product3.getCode()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
