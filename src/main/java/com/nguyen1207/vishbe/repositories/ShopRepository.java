package com.nguyen1207.vishbe.repositories;

import com.nguyen1207.vishbe.entities.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {
    Optional<Shop> findByName(String name);

    Optional<Shop> findById(String shopId);
}
