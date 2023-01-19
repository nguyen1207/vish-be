package com.nguyen1207.vishbe.entities.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nguyen1207.vishbe.entities.invoice.Invoice;
import com.nguyen1207.vishbe.entities.review.Review;
import com.nguyen1207.vishbe.entities.review.Vote;
import com.nguyen1207.vishbe.entities.shop.Shop;
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
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String userId;

    @OneToOne(mappedBy = "owner", cascade = {CascadeType.ALL})
    @JsonManagedReference
    @ToString.Exclude
    private Shop shop;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<UserAddress> addresses;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "cartId", nullable = false)
    @JsonManagedReference
    @ToString.Exclude
    private Cart cart;

    @ManyToMany(mappedBy = "followers", cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, targetEntity = Shop.class)
    @ToString.Exclude
    private Set<Shop> followedShops;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Vote> votes;

    @Column(unique = true)
    private String email;

    private String fullName;

    private String avatarUrl;

    @Column(unique = true)
    private String phone;

    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
