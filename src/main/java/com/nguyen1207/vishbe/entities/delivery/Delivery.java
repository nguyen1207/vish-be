package com.nguyen1207.vishbe.entities.delivery;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import com.nguyen1207.vishbe.enums.DeliveryOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deliveryId;

    @OneToMany(mappedBy = "delivery", cascade = {CascadeType.ALL})
    private List<DeliveryLog> deliveryLogs;

    @OneToOne(mappedBy = "delivery", optional = false)
    private SubInvoice subInvoice;

    private long cost;

    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOption;
}
