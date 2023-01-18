package com.nguyen1207.vishbe.entities.product;

import com.nguyen1207.vishbe.entities.image.HasImages;
import com.nguyen1207.vishbe.entities.invoice.InvoiceLine;
import com.nguyen1207.vishbe.entities.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;

@Entity
@Data
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
    private List<Variation> variations;

    @ManyToMany
    @JoinTable(
            name = "ProductCategory",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private Set<Category> categories;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "hasImagesId", nullable = false)
    private HasImages hasImages;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<ProductValueDate> valueDates;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<ProductValueString> valueStrings;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<ProductValueInt> valueInts;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<ProductValueFloat> valueFloats;

    @OneToMany(mappedBy = "product")
    private List<InvoiceLine> purchasedInvoiceLines;

    private String name;

    private String brand;

    private float rating;
}
