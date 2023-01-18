package com.nguyen1207.vishbe.entities.shop;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID voucherId;

    @ManyToOne
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "shopVoucher")
    private List<SubInvoice> ordersUsedVoucher;

    private long minimumSpend;

    private int usesPerUser;

    private int quantity;

    private int totalUsed;

    private Date endDate;
}
