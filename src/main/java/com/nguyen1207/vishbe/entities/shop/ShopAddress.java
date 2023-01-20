package com.nguyen1207.vishbe.entities.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nguyen1207.vishbe.entities.user.Address;
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
public class ShopAddress {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String addressId;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @MapsId
    @JoinColumn(name = "addressId")
    @JsonManagedReference
    private Address address;

    @OneToOne(optional = false)
    @JoinColumn(name = "shopId", nullable = false)
    @JsonBackReference
    private Shop shop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopAddress that = (ShopAddress) o;
        return addressId != null && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
