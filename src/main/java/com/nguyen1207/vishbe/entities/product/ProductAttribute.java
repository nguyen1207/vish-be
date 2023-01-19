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
public class ProductAttribute {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String attributeId;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueDate> valueDates;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueString> valueStrings;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueInt> valueInts;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueFloat> valueFloats;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductAttribute that = (ProductAttribute) o;
        return attributeId != null && Objects.equals(attributeId, that.attributeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
