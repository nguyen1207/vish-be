package com.nguyen1207.vishbe.entities.user;

import com.nguyen1207.vishbe.entities.invoice.Invoice;
import com.nguyen1207.vishbe.entities.review.Review;
import com.nguyen1207.vishbe.entities.review.Vote;
import com.nguyen1207.vishbe.entities.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @OneToOne(mappedBy = "owner", cascade = {CascadeType.ALL})
    private Shop shop;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<UserAddress> addresses;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @ManyToMany(mappedBy = "followers", cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, targetEntity = Shop.class)
    private Set<Shop> followedShops;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.ALL})
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Vote> votes;

    @Column(unique = true)
    private String email;

    private String fullName;

    private String avatarUrl;

    private String phone;

    private String password;
}
