package com.nguyen1207.vishbe.entities.invoice;

import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.entities.user.UserAddress;
import com.nguyen1207.vishbe.entities.voucher.VishVoucher;
import com.nguyen1207.vishbe.enums.PaymentMethod;
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
public class Invoice {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(name = "addressId", nullable = false)
    private UserAddress address;

    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<SubInvoice> subInvoices;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "vishVoucherId")
    private VishVoucher vishVoucher;

    private Date createdAt;

    private long total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Invoice invoice = (Invoice) o;
        return orderId != null && Objects.equals(orderId, invoice.orderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
