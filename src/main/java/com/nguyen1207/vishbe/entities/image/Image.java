package com.nguyen1207.vishbe.entities.image;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String imageId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "foreignId", nullable = false)
    private HasImages hasImages;

    private String url;
}
