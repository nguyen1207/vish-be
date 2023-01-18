package com.nguyen1207.vishbe.entities.user;

import com.nguyen1207.vishbe.entities.product.Product;
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
@IdClass(CartRowId.class)
public class CartRow {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String productId;

    @Id
    private String cartId;

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
