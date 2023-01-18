package com.nguyen1207.vishbe.entities.shop;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopVoucher {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String voucherId;

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
