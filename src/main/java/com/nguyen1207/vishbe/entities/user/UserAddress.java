package com.nguyen1207.vishbe.entities.user;

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
public class UserAddress {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String addressId;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "addressId")
    private Address address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
