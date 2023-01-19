package com.nguyen1207.vishbe.entities.shop;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private List<SubInvoice> ordersUsedVoucher;

    private long minimumSpend;

    private int usesPerUser;

    private int quantity;

    private int totalUsed;

    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopVoucher that = (ShopVoucher) o;
        return voucherId != null && Objects.equals(voucherId, that.voucherId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
