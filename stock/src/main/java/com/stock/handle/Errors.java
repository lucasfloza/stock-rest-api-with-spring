package com.stock.handle;
import org.springframework.validation.FieldError;

public class Errors {
    private String field;
    private String error;

    public Errors(FieldError fieldError){
        this.field= fieldError.getField();
        this.error= fieldError.getDefaultMessage();
    }

    public Errors(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
