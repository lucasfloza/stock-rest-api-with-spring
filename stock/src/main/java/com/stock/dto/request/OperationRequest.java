package com.stock.dto.request;

import com.stock.domain.Operation;
import com.stock.domain.OperationType;
import com.stock.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class OperationRequest {

    private LocalDateTime date = LocalDateTime.now();
    private Integer quantity;
    private BigDecimal unitPriceApplied;
    private OperationType operationType;
    private String productCode;

    public OperationRequest(Integer quantity, BigDecimal unitPriceApplied, OperationType operationType, String productCode) {
        this.quantity = quantity;
        this.unitPriceApplied = unitPriceApplied;
        this.operationType = operationType;
        this.productCode = productCode;
    }

    public Operation toModel(Product product) {
        Operation operation = new Operation();
        operation.setDate(date);
        operation.setQuantity(quantity);
        operation.setUnitPriceApplied(unitPriceApplied);
        operation.setOperationType(operationType);
        operation.setProduct(product);
        return operation;
    }
}
