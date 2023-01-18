package com.nguyen1207.vishbe.entities.voucher;

import com.nguyen1207.vishbe.entities.invoice.Invoice;
import com.nguyen1207.vishbe.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "type",
        discriminatorType = DiscriminatorType.STRING
)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VishVoucher {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String vishVoucherId;

    @OneToMany(mappedBy = "vishVoucher")
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
}
