package com.nguyen1207.vishbe.entities.shop;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@DiscriminatorValue("percent")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ShopPercentVoucher extends ShopVoucher {
    private int salePercent;

    private long maximumAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopPercentVoucher that = (ShopPercentVoucher) o;
        return getVoucherId() != null && Objects.equals(getVoucherId(), that.getVoucherId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
