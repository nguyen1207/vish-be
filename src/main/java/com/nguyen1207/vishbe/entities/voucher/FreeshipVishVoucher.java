package com.nguyen1207.vishbe.entities.voucher;

import com.nguyen1207.vishbe.enums.DeliveryOption;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("freeship")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FreeshipVishVoucher extends VishVoucher {
    private long amount;

    @ElementCollection
    @CollectionTable(
            name = "DeliveryOption",
            joinColumns = @JoinColumn(name = "vishVoucherId")
    )
    @OrderColumn
    private List<DeliveryOption> allowedDeliveryOptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FreeshipVishVoucher that = (FreeshipVishVoucher) o;
        return getVishVoucherId() != null && Objects.equals(getVishVoucherId(), that.getVishVoucherId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
