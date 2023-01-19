package com.nguyen1207.vishbe.entities.invoice;

import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.refund.RefundRequest;
import com.nguyen1207.vishbe.entities.review.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InvoiceLine that = (InvoiceLine) o;
        return subInvoiceId != null && Objects.equals(subInvoiceId, that.subInvoiceId)
                && productId != null && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subInvoiceId, productId);
    }
}
