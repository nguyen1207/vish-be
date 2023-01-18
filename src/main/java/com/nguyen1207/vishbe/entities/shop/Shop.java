package com.nguyen1207.vishbe.entities.shop;

import com.nguyen1207.vishbe.entities.invoice.SubInvoice;
import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.enums.ShopType;
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
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID shopId;

    @OneToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User owner;

    @OneToOne(mappedBy = "shop", cascade = {CascadeType.ALL}, optional = false)
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
    private Set<User> followers;

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.ALL})
    private List<Product> products;

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.ALL})
    private List<ShopVoucher> vouchers;

    @OneToMany(mappedBy = "shop")
    private List<SubInvoice> subInvoices;

    @Column(unique = true)
    private String name;

    private String avatarUrl;

    private String description;

    @Enumerated(EnumType.STRING)
    private ShopType shopType;

    private float rating;
}
