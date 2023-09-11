package com.stock.dto.response;

import com.stock.domain.ProductEntry;

public class ProductEntryResponse {
    private Long productId;
    private Integer entryQuantity;

    public ProductEntryResponse(ProductEntry productEntry) {
        this.productId = productEntry.getProduct().getId();
        this.entryQuantity=productEntry.getQuantity();
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getEntryQuantity() {
        return entryQuantity;
    }
}
