package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductValueInt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID valueId;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "attributeId", nullable = false)
    private ProductAttribute productAttribute;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private int value;

    private String unit;
}
