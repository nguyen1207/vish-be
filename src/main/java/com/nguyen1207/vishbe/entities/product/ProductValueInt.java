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
public class ProductValueInt {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String valueId;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "attributeId", nullable = false)
    private ProductAttribute productAttribute;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private int value;

    private String unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductValueInt that = (ProductValueInt) o;
        return valueId != null && Objects.equals(valueId, that.valueId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
