package com.nguyen1207.vishbe.entities.image;

import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.refund.RefundRequest;
import com.nguyen1207.vishbe.entities.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HasImages {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "hasImages", optional = false)
    private Product product;

    @OneToOne(mappedBy = "hasImages", optional = false)
    private Review review;

    @OneToOne(mappedBy = "hasImages", optional = false)
    private RefundRequest refundRequest;

    @OneToMany(mappedBy = "hasImages", cascade = {CascadeType.ALL})
    private List<Image> images;
}
