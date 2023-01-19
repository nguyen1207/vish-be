package com.nguyen1207.vishbe.entities.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

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
public class Category {
    @Id
    private String name;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private Set<Product> products;

    private String imageUrl;

    @OneToMany(mappedBy = "superCategory", cascade = {CascadeType.ALL})
    @ToString.Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return name != null && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
