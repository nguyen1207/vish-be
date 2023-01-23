package com.nguyen1207.vishbe.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
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
public class Category {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String categoryId;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private Set<Product> products;

    @Nullable
    private String imageUrl;

    @OneToMany(mappedBy = "superCategory", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnoreProperties("superCategory")
    private List<Category> subCategories;

    @ManyToOne
    @JoinColumn(name = "superCategoryId")
    @ToString.Exclude
    @JsonIgnoreProperties("subCategories")
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
