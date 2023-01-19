package com.nguyen1207.vishbe.entities.image;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;
        return imageId != null && Objects.equals(imageId, image.imageId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
