package com.stock.dto.response;

import com.stock.domain.Operation;
import com.stock.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OperationResponse {
    private Long idOperation;
    private LocalDateTime date;
    private Integer quantity;
    private OperationType operationType;
    private String productCode;

    public OperationResponse(Operation operation) {
        this.idOperation = operation.getId();
        this.date = operation.getDate();
        this.quantity = operation.getQuantity();
        this.operationType = operation.getOperationType();
        this.productCode = operation.getProduct().getCode();
    }
}
