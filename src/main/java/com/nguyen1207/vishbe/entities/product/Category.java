package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

    private String imageUrl;

    @OneToMany(mappedBy = "superCategory", cascade = {CascadeType.ALL})
    private List<Category> subCategories;

    @ManyToOne
    @JoinColumn(name = "superCategoryName")
    private Category superCategory;

    @PreRemove
    private void removeProductInCategories() {
        for (Product p : products) {
            p.getCategories().remove(this);
        }
    }
}
