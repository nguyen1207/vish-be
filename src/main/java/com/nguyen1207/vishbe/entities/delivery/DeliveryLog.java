package com.nguyen1207.vishbe.entities.delivery;

import com.nguyen1207.vishbe.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
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
}
