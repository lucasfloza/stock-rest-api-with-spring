package com.stock.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer minimumQuantity;
    @Column(nullable = false)
    private Integer maximumQuantity;
    @Column(nullable = false)
    private LocalDateTime createdIn;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOut> productOuts= new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductEntry> productEntries = new ArrayList<>();
    public Product() {
    }

    public Product(String name, String description, Integer minimumQuantity, Integer maximumQuantity) {
        this.name = name;
        this.description = description;
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
        this.createdIn  = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Integer getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMaximumQuantity(Integer maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

    public LocalDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn) {
        this.createdIn = createdIn;
    }

    public void addProductEntry(ProductEntry productEntry){
        this.productEntries.add(productEntry);
    }
    public void addProductOutput(ProductOut productOut){
        this.productOuts.add(productOut);
    }
}
