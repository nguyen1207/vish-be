package com.nguyen1207.vishbe.entities.voucher;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@DiscriminatorValue("amount")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AmountVishVoucher extends VishVoucher {
    private long amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AmountVishVoucher that = (AmountVishVoucher) o;
        return getVishVoucherId() != null && Objects.equals(getVishVoucherId(), that.getVishVoucherId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
