package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private List<VariationValue> variationValues;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Variation variation = (Variation) o;
        return variationId != null && Objects.equals(variationId, variation.variationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
