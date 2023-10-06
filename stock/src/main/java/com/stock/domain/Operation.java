package com.stock.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "operations")
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Integer quantity;
    private BigDecimal unitPriceApplied;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

