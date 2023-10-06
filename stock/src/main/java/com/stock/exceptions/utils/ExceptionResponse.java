package com.stock.exceptions.utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ExceptionResponse {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

}
