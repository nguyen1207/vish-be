package com.nguyen1207.vishbe.entities.delivery;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import com.nguyen1207.vishbe.enums.DeliveryOption;
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
public class Delivery {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String deliveryId;

    @OneToMany(mappedBy = "delivery", cascade = {CascadeType.ALL})
    private List<DeliveryLog> deliveryLogs;

    @OneToOne(mappedBy = "delivery", optional = false)
    private SubInvoice subInvoice;

    private long cost;

    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOption;
}
