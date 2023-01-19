package com.nguyen1207.vishbe.entities.delivery;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import com.nguyen1207.vishbe.enums.DeliveryOption;
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
public class Delivery {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String deliveryId;

    @OneToMany(mappedBy = "delivery", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<DeliveryLog> deliveryLogs;

    @OneToOne(mappedBy = "delivery", optional = false)
    private SubInvoice subInvoice;

    private long cost;

    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Delivery delivery = (Delivery) o;
        return deliveryId != null && Objects.equals(deliveryId, delivery.deliveryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
