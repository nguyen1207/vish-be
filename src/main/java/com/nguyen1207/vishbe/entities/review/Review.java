package com.nguyen1207.vishbe.entities.review;

import com.nguyen1207.vishbe.entities.image.HasImages;
import com.nguyen1207.vishbe.entities.invoice.InvoiceLine;
import com.nguyen1207.vishbe.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reviewId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User author;

    @Column(nullable = false, insertable = false, updatable = false)
    private UUID subInvoiceId;

    @Column(nullable = false, insertable = false, updatable = false)
    private UUID productId;

    @OneToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "subInvoiceId", referencedColumnName = "subInvoiceId", nullable = false, unique = true),
            @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false, unique = true),
    })
    private InvoiceLine invoiceLine;

    @OneToMany(mappedBy = "review", cascade = {CascadeType.ALL})
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
}
