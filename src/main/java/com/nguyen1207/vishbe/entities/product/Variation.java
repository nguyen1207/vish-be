package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID variationId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "variation", cascade = {CascadeType.ALL})
    private List<VariationValue> variationValues;

    private String name;
}
