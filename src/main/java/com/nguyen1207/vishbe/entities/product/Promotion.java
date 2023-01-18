package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String promotionId;

    @OneToMany(mappedBy = "promotion")
    private List<VariationValue> variationValues;

    @OneToMany(mappedBy = "promotion")
    private List<VariationValue> variationValuesApplied;

    private int salePercent;

    private Date endDate;
}
