package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VariationValue that = (VariationValue) o;
        return valueId != null && Objects.equals(valueId, that.valueId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
