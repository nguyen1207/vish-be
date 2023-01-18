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
public class ProductAttribute {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String attributeId;

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
