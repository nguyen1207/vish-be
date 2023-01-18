package com.nguyen1207.vishbe.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "addressId")
    private Address address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
