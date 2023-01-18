package com.nguyen1207.vishbe.entities.shop;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("percent")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopPercentVoucher extends ShopVoucher {
    private int salePercent;

    private long maximumAmount;
}
