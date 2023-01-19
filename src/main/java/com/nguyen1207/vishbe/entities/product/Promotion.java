package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String promotionId;

    @OneToMany(mappedBy = "promotion")
    @ToString.Exclude
    private List<VariationValue> variationValues;

    @OneToMany(mappedBy = "promotion")
    @ToString.Exclude
    private List<VariationValue> variationValuesApplied;

    private int salePercent;

    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Promotion promotion = (Promotion) o;
        return promotionId != null && Objects.equals(promotionId, promotion.promotionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
