package com.nguyen1207.vishbe.entities.image;

import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.refund.RefundRequest;
import com.nguyen1207.vishbe.entities.review.Review;
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
public class HasImages {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne(mappedBy = "hasImages", optional = false)
    private Product product;

    @OneToOne(mappedBy = "hasImages", optional = false)
    private Review review;

    @OneToOne(mappedBy = "hasImages", optional = false)
    private RefundRequest refundRequest;

    @OneToMany(mappedBy = "hasImages", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Image> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HasImages hasImages = (HasImages) o;
        return id != null && Objects.equals(id, hasImages.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
