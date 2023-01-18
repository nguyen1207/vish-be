package com.nguyen1207.vishbe.entities.voucher;

import com.nguyen1207.vishbe.enums.DeliveryOption;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@DiscriminatorValue("freeship")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FreeshipVishVoucher extends VishVoucher {
    private long amount;

    @ElementCollection
    @CollectionTable(
            name = "DeliveryOption",
            joinColumns = @JoinColumn(name = "vishVoucherId")
    )
    @OrderColumn
    private List<DeliveryOption> allowedDeliveryOptions;
}
