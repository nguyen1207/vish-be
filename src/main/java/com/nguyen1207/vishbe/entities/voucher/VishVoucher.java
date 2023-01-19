package com.nguyen1207.vishbe.entities.voucher;

import com.nguyen1207.vishbe.entities.invoice.Invoice;
import com.nguyen1207.vishbe.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "type",
        discriminatorType = DiscriminatorType.STRING
)
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VishVoucher {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String vishVoucherId;

    @OneToMany(mappedBy = "vishVoucher")
    @ToString.Exclude
    private List<Invoice> invoicesUsedVoucher;

    private int usesPerUser;

    @ElementCollection
    @CollectionTable(
            name = "PaymentMethod",
            joinColumns = @JoinColumn(name = "vishVoucherId")
    )
    @OrderColumn
    private List<PaymentMethod> allowedPaymentMethods;

    private int quantity;

    private int totalUsed;

    private long minimumSpend;

    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VishVoucher that = (VishVoucher) o;
        return vishVoucherId != null && Objects.equals(vishVoucherId, that.vishVoucherId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
