package com.nguyen1207.vishbe.entities.shop;

import com.nguyen1207.vishbe.entities.user.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopAddress {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String addressId;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "addressId")
    private Address address;

    @OneToOne(optional = false)
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;
}
