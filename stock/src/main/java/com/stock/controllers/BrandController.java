package com.stock.controllers;

import com.stock.domain.Brand;
import com.stock.dto.request.BrandRequest;
import com.stock.dto.response.BrandResponse;
import com.stock.services.BrandService;
import com.stock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{idOrCode}")
    public ResponseEntity<?> get(@PathVariable String idOrCode) {
        Brand brand = brandService.findByIdOrCode(idOrCode);
        return ResponseEntity.status(HttpStatus.OK).body(new BrandResponse(brand));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Brand> allBrands = brandService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allBrands.stream().map(BrandResponse::new).toList());
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody BrandRequest brandRequest, UriComponentsBuilder uriBuilder) {
        Brand brand = brandService.create(brandRequest);
        URI uri = uriBuilder.path("/brand").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new BrandResponse(brand));
    }

    @PutMapping(value = "/{idOrCode}")
    public ResponseEntity<?> updateBrand(@PathVariable String idOrCode, @RequestBody BrandRequest brandRequest, UriComponentsBuilder uriBuilder) {
        Brand brand = brandService.update(idOrCode, brandRequest);
        productService.updateProductsByBrand(brand);
        return ResponseEntity.status(HttpStatus.OK).body(new BrandResponse(brand));
    }

    @DeleteMapping(value = "/{idOrCode}")
    public ResponseEntity<?> deleteBrand(@PathVariable String idOrCode) {
        brandService.delete(idOrCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
