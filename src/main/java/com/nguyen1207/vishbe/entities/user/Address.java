package com.nguyen1207.vishbe.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
