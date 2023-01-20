package com.nguyen1207.vishbe.entities.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String shopId;

    @OneToOne
    @JoinColumn(name = "ownerId", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    private User owner;

    @OneToOne(mappedBy = "shop", cascade = {CascadeType.ALL}, optional = false)
    @JsonManagedReference
    @ToString.Exclude
    private ShopAddress address;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "Follow",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "shopId")
    )
    @ToString.Exclude
    private Set<User> followers;

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Product> products;

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ShopVoucher> vouchers;

    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    @JsonManagedReference
    private List<SubInvoice> subInvoices;

    @Column(unique = true)
    private String name;

    private String avatarUrl;

    private String description;

    private float rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shop shop = (Shop) o;
        return shopId != null && Objects.equals(shopId, shop.shopId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
