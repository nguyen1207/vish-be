package com.nguyen1207.vishbe.entities.user;

import com.nguyen1207.vishbe.entities.product.Product;
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
@IdClass(CartRowId.class)
public class CartRow {
    @Id
    private UUID productId;

    @Id
    private UUID cartId;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @OneToOne(optional = false)
    @MapsId("productId")
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private int quantity;
}
