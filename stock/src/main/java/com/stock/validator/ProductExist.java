package com.stock.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductExistValidator.class)
public @interface ProductExist {
    String message() default "There is no product registration with this identifier";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
