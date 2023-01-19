package com.nguyen1207.vishbe.entities.delivery;

import com.nguyen1207.vishbe.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryLog {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String deliveryLogId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private String description;

    private Date createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeliveryLog that = (DeliveryLog) o;
        return deliveryLogId != null && Objects.equals(deliveryLogId, that.deliveryLogId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
