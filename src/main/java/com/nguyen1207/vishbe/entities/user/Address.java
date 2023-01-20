package com.nguyen1207.vishbe.entities.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nguyen1207.vishbe.entities.shop.ShopAddress;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String addressId;

    @OneToOne(mappedBy = "address", optional = false, cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @JsonBackReference
    private UserAddress userAddress;

    @OneToOne(mappedBy = "address", optional = false)
    @ToString.Exclude
    @JsonBackReference
    private ShopAddress shopAddress;

    private String fullName;

    private String phone;

    private String city;

    private String district;

    private String ward;

    private String details;

    private boolean isDefault;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return addressId != null && Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
