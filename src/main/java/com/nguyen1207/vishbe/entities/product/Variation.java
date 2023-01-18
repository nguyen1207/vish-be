package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variation {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String variationId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "variation", cascade = {CascadeType.ALL})
    private List<VariationValue> variationValues;

    private String name;
}
