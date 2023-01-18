package com.nguyen1207.vishbe.entities.invoice;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class InvoiceLineId implements Serializable {
    private UUID subInvoiceId;

    private UUID productId;
}
