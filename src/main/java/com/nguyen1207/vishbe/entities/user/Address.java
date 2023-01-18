package com.nguyen1207.vishbe.entities.user;

import com.nguyen1207.vishbe.entities.shop.ShopAddress;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Address {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String addressId;

    @OneToOne(mappedBy = "address", optional = false)
    private UserAddress userAddress;

    @OneToOne(mappedBy = "address", optional = false)
    private ShopAddress shopAddress;

    private String fullName;

    private String phone;

    private String city;

    private String district;

    private String ward;

    private String details;

    private boolean isDefault;
}
