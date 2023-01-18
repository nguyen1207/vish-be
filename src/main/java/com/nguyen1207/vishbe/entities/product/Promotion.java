package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID promotionId;

    @OneToMany(mappedBy = "promotion")
    private List<VariationValue> variationValues;

    @OneToMany(mappedBy = "promotion")
    private List<VariationValue> variationValuesApplied;

    private int salePercent;

    private Date endDate;
}
