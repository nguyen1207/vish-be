package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductValueDate {
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

    private Date value;
}
