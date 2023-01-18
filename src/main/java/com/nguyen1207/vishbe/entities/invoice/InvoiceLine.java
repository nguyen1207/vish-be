package com.nguyen1207.vishbe.entities.invoice;

import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.refund.RefundRequest;
import com.nguyen1207.vishbe.entities.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(InvoiceLineId.class)
public class InvoiceLine {
    @Id
    private String subInvoiceId;

    @Id
    private String productId;

    @ManyToOne(optional = false)
    @MapsId("subInvoiceId")
    @JoinColumn(name = "subInvoiceId", nullable = false)
    private SubInvoice subInvoice;

    @ManyToOne(optional = false)
    @MapsId("productId")
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @OneToOne(mappedBy = "invoiceLine")
    private Review review;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "refundRequestId")
    private RefundRequest refundRequest;

    private int quantity;

    private int discountPercent;

    private long originalPrice;
}
