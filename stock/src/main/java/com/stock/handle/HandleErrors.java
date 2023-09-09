package com.stock.handle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleErrors {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException e){
        List<Errors> erros=e.getBindingResult().getFieldErrors().stream().map(Errors::new).collect(Collectors.toList());
        erros.addAll(e.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(
                        err-> new Errors(
                                err.getObjectName().substring(5,12).toLowerCase(),
                        err.getDefaultMessage())
        ).toList());
        return ResponseEntity.badRequest().body(erros);
    }
}
