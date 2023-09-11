package com.stock.dto.response;

import com.stock.domain.ProductOut;

public class ProductOutResponse {
    private Long productId;
    private Integer outputQuantity;

    public ProductOutResponse(ProductOut ProductOut) {
        this.productId=ProductOut.getProduct().getId();
        this.outputQuantity=ProductOut.getQuantity();
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getOutputQuantity() {
        return outputQuantity;
    }
}
