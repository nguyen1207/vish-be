package com.nguyen1207.vishbe.entities.refund;

import com.nguyen1207.vishbe.entities.image.HasImages;
import com.nguyen1207.vishbe.entities.invoice.InvoiceLine;
import com.nguyen1207.vishbe.enums.RefundReason;
import com.nguyen1207.vishbe.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID refundRequestId;

    @OneToMany(mappedBy = "refundRequest")
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
}
