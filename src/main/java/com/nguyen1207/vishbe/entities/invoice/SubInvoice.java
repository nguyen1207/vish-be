package com.nguyen1207.vishbe.entities.invoice;

import com.nguyen1207.vishbe.entities.delivery.Delivery;
import com.nguyen1207.vishbe.entities.shop.Shop;
import com.nguyen1207.vishbe.entities.shop.ShopVoucher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
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
    private List<InvoiceLine> invoiceLines;

    @ManyToOne
    @JoinColumn(name = "shopVoucherId")
    private ShopVoucher shopVoucher;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

    private String note;
}
