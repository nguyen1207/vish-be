package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariationValue {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String valueId;

    @ManyToOne
    @JoinColumn(name = "variationId", nullable = false)
    private Variation variation;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "promotionId")
    private Promotion promotion;

    private String value;

    private int stock;

    private long originalPrice;

    private String imageUrl;
}
