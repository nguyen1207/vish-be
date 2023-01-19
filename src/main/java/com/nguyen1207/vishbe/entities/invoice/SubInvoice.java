package com.nguyen1207.vishbe.entities.invoice;

import com.nguyen1207.vishbe.entities.delivery.Delivery;
import com.nguyen1207.vishbe.entities.shop.Shop;
import com.nguyen1207.vishbe.entities.shop.ShopVoucher;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubInvoice {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String subInvoiceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoiceId", nullable = false)
    private Invoice invoice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "subInvoice", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<InvoiceLine> invoiceLines;

    @ManyToOne
    @JoinColumn(name = "shopVoucherId")
    private ShopVoucher shopVoucher;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubInvoice that = (SubInvoice) o;
        return subInvoiceId != null && Objects.equals(subInvoiceId, that.subInvoiceId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
