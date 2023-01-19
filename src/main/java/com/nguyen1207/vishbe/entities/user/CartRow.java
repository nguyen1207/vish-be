package com.nguyen1207.vishbe.entities.user;

import com.nguyen1207.vishbe.entities.product.Product;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartRow cartRow = (CartRow) o;
        return productId != null && Objects.equals(productId, cartRow.productId)
                && cartId != null && Objects.equals(cartId, cartRow.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }
}
