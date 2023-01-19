package com.nguyen1207.vishbe.entities.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String cartId;

    @OneToOne(mappedBy = "cart", optional = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<CartRow> cartRows;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return cartId != null && Objects.equals(cartId, cart.cartId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
