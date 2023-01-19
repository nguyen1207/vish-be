package com.nguyen1207.vishbe.entities.refund;

import com.nguyen1207.vishbe.entities.image.HasImages;
import com.nguyen1207.vishbe.entities.invoice.InvoiceLine;
import com.nguyen1207.vishbe.enums.RefundReason;
import com.nguyen1207.vishbe.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundRequest {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String refundRequestId;

    @OneToMany(mappedBy = "refundRequest")
    @ToString.Exclude
    private List<InvoiceLine> invoiceLines;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hasImagesId")
    private HasImages hasImages;

    @Enumerated(EnumType.STRING)
    private RefundReason reason;

    private String refundTo;

    private String description;

    private RefundStatus status;

    private Date createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RefundRequest that = (RefundRequest) o;
        return refundRequestId != null && Objects.equals(refundRequestId, that.refundRequestId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
