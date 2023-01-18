package com.nguyen1207.vishbe.entities.user;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CartRowId implements Serializable {
    private UUID productId;

    private UUID cartId;
}
