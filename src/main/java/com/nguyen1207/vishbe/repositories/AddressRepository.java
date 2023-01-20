package com.nguyen1207.vishbe.repositories;

import com.nguyen1207.vishbe.entities.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, String> {
    @Modifying
    @Query("UPDATE Address a SET a.isDefault = false WHERE a.addressId IN (" +
            "SELECT ua.addressId FROM UserAddress ua WHERE ua.user.userId = :userId) " +
            "AND a.isDefault = true")
    void unSetDefaultAddress(@Param("userId") String userId);
}
