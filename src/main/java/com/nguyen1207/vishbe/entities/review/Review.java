package com.nguyen1207.vishbe.entities.review;

import com.nguyen1207.vishbe.entities.image.HasImages;
import com.nguyen1207.vishbe.entities.invoice.InvoiceLine;
import com.nguyen1207.vishbe.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String reviewId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User author;

    @Column(nullable = false, insertable = false, updatable = false)
    private String subInvoiceId;

    @Column(nullable = false, insertable = false, updatable = false)
    private String productId;

    @OneToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "subInvoiceId", referencedColumnName = "subInvoiceId", nullable = false, unique = true),
            @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false, unique = true),
    })
    private InvoiceLine invoiceLine;

    @OneToMany(mappedBy = "review", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Vote> votes;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hasImagesId")
    private HasImages hasImages;

    private float rating;

    private String shortReview;

    private String details;

    private Date createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Review review = (Review) o;
        return reviewId != null && Objects.equals(reviewId, review.reviewId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
