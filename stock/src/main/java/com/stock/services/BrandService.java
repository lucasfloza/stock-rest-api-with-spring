package com.stock.services;

import com.stock.domain.Brand;
import com.stock.dto.request.BrandRequest;
import com.stock.exceptions.ResourceNotFoundException;
import com.stock.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public Brand findByIdOrCode(String idOrCode) {
        Brand brand;
        try {
            Long id = Long.parseLong(idOrCode);
            brand = brandRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("No brands found with this ID!"));
        } catch (NumberFormatException e) {
            brand = brandRepository.findByCode(idOrCode).orElseThrow(() ->
                    new ResourceNotFoundException("No brands found with this CODE!"));
        }
        return brand;
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Brand create(BrandRequest brandRequest) {
        Brand brand = brandRequest.toModel();
        if (brand.getCode() == null) {
            int size = findAll().size();
            String numberFormated = String.format("%05d", size);
            brand.setCode("B" + numberFormated);
        } else {
            verifyCode(brandRequest.getCode());
        }
        return brandRepository.save(brand);
    }

    public Brand update(String idOrCode, BrandRequest brandRequest) {
        verifyCode(brandRequest.getCode());
        Brand brand = findByIdOrCode(idOrCode);
        return brandRepository.save(brandRequest.toModel(brand));
    }

    public void delete(String idOrCode) {
        Brand brand = findByIdOrCode(idOrCode);
        try {
            brandRepository.delete(brand);
        } catch (Exception e) {
            throw new IllegalArgumentException("Try to delete a brand linked to a product, please unlink it first.");
        }

    }

    private void verifyCode(String code) {
        if (brandRepository.existsByCode(code))
            throw new ResourceNotFoundException("There is already a brand registered with this code");
    }
}
