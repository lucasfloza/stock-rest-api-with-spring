package com.stock.services;

import com.stock.domain.Operation;
import com.stock.domain.OperationType;
import com.stock.domain.Product;
import com.stock.dto.request.OperationRequest;
import com.stock.exceptions.NegativeQuantityException;
import com.stock.exceptions.ResourceNotFoundException;
import com.stock.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ProductService productService;

    public Operation findById(Long id) {
        return operationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No operations found with this ID!"));
    }

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    public Operation create(OperationRequest operationRequest) {
        Product product = productService.findByIdOrCode(operationRequest.getProductCode());
        product.setDateUpdate(operationRequest.getDate());

        //PRICE APPLIED MUST BE GREATER OR EQUAL TO ZERO
        if (operationRequest.getUnitPriceApplied().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price applied must be greater than or equal to zero");
        }
        product.setUnitPrice(operationRequest.getUnitPriceApplied());
        Integer quantity = product.getQuantity() != null ? product.getQuantity() : 0;

        //QUANTITY MUST BE GREATER THAN ZERO
        if (operationRequest.getQuantity() > 0) {
            if (operationRequest.getOperationType() == OperationType.INPUT) {
                quantity += operationRequest.getQuantity();
                product.setQuantity(quantity);
            } else if (operationRequest.getOperationType() == OperationType.OUTPUT) {
                if (quantity - operationRequest.getQuantity() < 0) {
                    int i = quantity - operationRequest.getQuantity();
                    String str = "Product quantity will be negative in stock. [product: " + product.getName() + " , quantity: " + i + "].";
                    throw new NegativeQuantityException(str);
                } else {
                    quantity -= operationRequest.getQuantity();
                    product.setQuantity(quantity);
                }
            }
        } else {
            throw new NegativeQuantityException("To subtract the quantity of products in stock, change the operation type to OUTPUT");
        }

        Product productUpdated = productService.updateWithOperation(product);
        return operationRepository.save(operationRequest.toModel(productUpdated));
    }

}
