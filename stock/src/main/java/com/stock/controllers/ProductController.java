package com.stock.controllers;

import com.stock.domain.Product;
import com.stock.domain.ProductEntry;
import com.stock.domain.ProductOut;
import com.stock.dto.request.ProductEntryRequest;
import com.stock.dto.request.ProductOutRequest;
import com.stock.dto.request.ProductRequest;
import com.stock.dto.response.ProductEntryResponse;
import com.stock.dto.response.ProductOutResponse;
import com.stock.dto.response.ProductResponse;
import com.stock.handle.Errors;
import com.stock.repository.ProductRepository;
import com.stock.validator.ProductExist;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private EntityManager manager;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody @Valid ProductRequest productRequest, UriComponentsBuilder uriBuilder){
        Product product= productRequest.toModel();
        repository.save(product);
        URI uri=uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductResponse(product));
    }
    @PostMapping("/{id}/entries")
    @Transactional
    public ResponseEntity<?> makeEntry(@RequestBody @Valid ProductEntryRequest productEntryRequest, @PathVariable Long id, UriComponentsBuilder uriBuilder){
        Optional<Product> product = repository.findById(id);

        if(product.isEmpty()){
            Errors error=new Errors("Product", "There is no registration for this product");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        ProductEntry productEntry = productEntryRequest.toModel(product.get());
        manager.persist(productEntry);
        product.get().addProductEntry(productEntry);
        URI uri=uriBuilder.path("/products/{id}/entries/{idEntry}").buildAndExpand(Map.of("id",product.get().getId(),"idEntry",productEntry.getId())).toUri();
        return ResponseEntity.created(uri).body(new ProductEntryResponse(productEntry));
    }
    @PostMapping("/{id}/outputs")
    @Transactional
    public ResponseEntity<?> makeExit(@RequestBody @Valid ProductOutRequest productOutRequest, @PathVariable @ProductExist Long id, UriComponentsBuilder uriBuilder){
        Optional<Product> product = repository.findById(id);

        if(product.isEmpty()){
            Errors error = new Errors("Product", "There is no registration for this product");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Integer availableQuantity = repository.findByQuantidadeProduto(id);

        if (productOutRequest.getQuantity() > availableQuantity){
            Errors error = new Errors("Quantity","Quantity must be less than or equal to " + availableQuantity);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        ProductOut productOut = productOutRequest.toModel(product.get());
        manager.persist(productOut);
        product.get().addProductOutput(productOut);
        URI uri=uriBuilder.path("/products/{id}/outputs/{idSaida}").buildAndExpand(product.get().getId(), productOut.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductOutResponse(productOut));
    }
}
