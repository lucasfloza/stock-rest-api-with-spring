package com.stock.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "entry_product")
public class ProductEntry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    public ProductEntry() {
    }

    public ProductEntry(Product product, LocalDateTime entryDate, Double unitPrice, Integer quantity) {
        this.product = product;
        this.entryDate = entryDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
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

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
