package com.nguyen1207.vishbe.entities.product;

import com.nguyen1207.vishbe.entities.image.HasImages;
import com.nguyen1207.vishbe.entities.invoice.InvoiceLine;
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
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String productId;

    @ManyToOne
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Variation> variations;

    @ManyToMany
    @JoinTable(
            name = "ProductCategory",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    @ToString.Exclude
    private Set<Category> categories;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "hasImagesId", nullable = false)
    private HasImages hasImages;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueDate> valueDates;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueString> valueStrings;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueInt> valueInts;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ProductValueFloat> valueFloats;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<InvoiceLine> purchasedInvoiceLines;

    private String name;

    private String brand;

    private float rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productId != null && Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
