package com.stock.dto.response;

import com.stock.domain.Product;

public class ProductResponse {
    private Long productId;
    private String name;
    private Integer quantity;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
