package com.stock.services;

import com.stock.domain.Brand;
import com.stock.domain.Category;
import com.stock.domain.Product;
import com.stock.dto.request.ProductRequest;
import com.stock.exceptions.ResourceNotFoundException;
import com.stock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findByIdOrCode(String idOrCode) {
        Product product;
        try {
            Long id = Long.parseLong(idOrCode);
            product = productRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("No products found with this ID!"));
        } catch (NumberFormatException e) {
            product = productRepository.findByCode(idOrCode).orElseThrow(() ->
                    new ResourceNotFoundException("No products found with this CODE!"));
        }
        return product;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(ProductRequest productRequest, Brand brand, Category category) {
        Product product = productRequest.toModel(brand,category);

        if (product.getCode() == null) {
            int size = findAll().size();
            String numberFormated = String.format("%05d", size);
            product.setCode("P" + numberFormated);
        } else {
            verifyCode(product.getCode());
        }
        return productRepository.save(product);
    }

    public Product update(String idOrCode, ProductRequest productRequest, Brand brand, Category category) {
        verifyCode(productRequest.getCode());
        Product product = findByIdOrCode(idOrCode);
        return productRepository.save(productRequest.toModel(product,brand,category));
    }

    public void delete(String idOrCode) {
        Product product = findByIdOrCode(idOrCode);
        productRepository.delete(product);
    }

    private void verifyCode(String code) {
        if (productRepository.existsByCode(code))
            throw new ResourceNotFoundException("There is already a product registered with this code");
    }

    public void updateProductsByBrand(Brand brand) {
        List<Product> allProducts = findAll();
        for(Product product: allProducts ){
            if(Objects.equals(product.getBrandCode().getId(), brand.getId())){
                product.setBrandCode(brand);
                productRepository.save(product);
            }
        }
    }
    public void updateProductsByCategory(Category category){
        List<Product> allProducts = findAll();
        for(Product product: allProducts ){
            if(Objects.equals(product.getCategoryCode().getId(), category.getId())){
                product.setCategoryCode(category);
                productRepository.save(product);
            }
        }
    }

    public Product updateWithOperation(Product product) {
        return productRepository.save(product);
    }
}
