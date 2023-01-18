package com.nguyen1207.vishbe.entities.shop;

import com.nguyen1207.vishbe.entities.user.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "addressId")
    private Address address;

    @OneToOne(optional = false)
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;
}
