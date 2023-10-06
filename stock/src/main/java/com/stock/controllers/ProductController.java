package com.stock.controllers;

import com.stock.domain.Brand;
import com.stock.domain.Category;
import com.stock.domain.Product;
import com.stock.dto.request.ProductRequest;
import com.stock.dto.response.ProductResponse;
import com.stock.services.BrandService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/{idOrCode}")
    public ResponseEntity<?> get(@PathVariable String idOrCode) {
        Product product = productService.findByIdOrCode(idOrCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse(product));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Product> allProducts = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts.stream().map(ProductResponse::new).toList());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest, UriComponentsBuilder uriBuilder) {
        Product product = newProduct(null, productRequest);
        URI uri = uriBuilder.path("/product").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new ProductResponse(product));
    }

    @PutMapping(value = "/{idOrCode}")
    public ResponseEntity<?> updateProduct(@PathVariable String idOrCode, @RequestBody ProductRequest productRequest, UriComponentsBuilder uriBuilder) {
        Product product = newProduct(idOrCode, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse(product));
    }

    @DeleteMapping(value = "/{idOrCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable String idOrCode) {
        productService.delete(idOrCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public Product newProduct(String idOrCode, ProductRequest productRequest) {
        Brand brand = productRequest.getBrandCode() != null ?
                brandService.findByIdOrCode(productRequest.getBrandCode()) : null;
        Category category = productRequest.getCategoryCode() != null ?
                categoryService.findByIdOrCode(productRequest.getCategoryCode()) : null;
        if (idOrCode != null)
            return productService.update(idOrCode, productRequest, brand, category);
        else
            return productService.create(productRequest, brand, category);
    }
}
