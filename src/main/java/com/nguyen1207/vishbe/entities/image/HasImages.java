package com.nguyen1207.vishbe.entities.image;

import com.nguyen1207.vishbe.entities.product.Product;
import com.nguyen1207.vishbe.entities.refund.RefundRequest;
import com.nguyen1207.vishbe.entities.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
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
    private List<Image> images;
}
