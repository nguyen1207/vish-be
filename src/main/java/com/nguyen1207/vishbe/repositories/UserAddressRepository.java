package com.nguyen1207.vishbe.repositories;

import com.nguyen1207.vishbe.entities.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {
    Optional<UserAddress> findById(String userId);
}
