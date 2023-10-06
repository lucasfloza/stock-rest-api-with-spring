package com.stock.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "products")
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime registrationDate;
    private LocalDateTime dateUpdate;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "brand_code")
    private Brand brandCode;
    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category categoryCode;
    private Integer quantity;

    @PreUpdate
    public void preUpdate() {
        this.dateUpdate = LocalDateTime.now();
    }
}
