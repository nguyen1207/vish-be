package com.nguyen1207.vishbe.entities.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Cart {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String cartId;

    @OneToOne(mappedBy = "cart", optional = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.ALL})
    private List<CartRow> cartRows;
}
