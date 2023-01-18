package com.nguyen1207.vishbe.entities.invoice;

import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.entities.user.UserAddress;
import com.nguyen1207.vishbe.entities.voucher.VishVoucher;
import com.nguyen1207.vishbe.enums.PaymentMethod;
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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(name = "addressId", nullable = false)
    private UserAddress address;

    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.ALL})
    private List<SubInvoice> subInvoices;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "vishVoucherId")
    private VishVoucher vishVoucher;

    private Date createdAt;

    private long total;
}
