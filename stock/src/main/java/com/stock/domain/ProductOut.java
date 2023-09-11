package com.stock.domain;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "output_product")
public class ProductOut {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private LocalDateTime outDate;

    public ProductOut() {
    }

    public ProductOut(Product product, Integer quantity, LocalDateTime outDate) {
        this.product = product;
        this.quantity = quantity;
        this.outDate = outDate;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }
}

