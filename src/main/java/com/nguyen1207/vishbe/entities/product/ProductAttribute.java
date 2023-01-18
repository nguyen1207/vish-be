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
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID attributeId;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    private List<ProductValueDate> valueDates;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    private List<ProductValueString> valueStrings;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    private List<ProductValueInt> valueInts;

    @OneToMany(mappedBy = "productAttribute", cascade = {CascadeType.ALL})
    private List<ProductValueFloat> valueFloats;

    private String name;
}
