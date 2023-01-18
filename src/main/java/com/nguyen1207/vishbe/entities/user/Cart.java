package com.nguyen1207.vishbe.entities.user;

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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToOne(mappedBy = "cart", optional = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.ALL})
    private List<CartRow> cartRows;
}
