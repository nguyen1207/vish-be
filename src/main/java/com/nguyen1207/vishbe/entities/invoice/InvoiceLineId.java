package com.nguyen1207.vishbe.entities.invoice;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvoiceLineId implements Serializable {
    private String subInvoiceId;

    private String productId;
}
