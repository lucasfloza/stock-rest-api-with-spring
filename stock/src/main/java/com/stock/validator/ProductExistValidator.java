package com.stock.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;


public class ProductExistValidator implements ConstraintValidator<ProductExist, Long>{
    private final EntityManager manager;

    public ProductExistValidator(EntityManager manager) {
        this.manager = manager;
    }

    public void initialize(ProductExist constraint) {
    }

    public boolean isValid(Long idProduct, ConstraintValidatorContext context) {
        String jpql = "select r from Product r where r.id=:id";
        Query query = manager.createQuery(jpql);
        query.setParameter("id", idProduct);
        List<?> result=query.getResultList();
        return !result.isEmpty();
    }
}
