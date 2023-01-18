package com.nguyen1207.vishbe.entities.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartRowId implements Serializable {
    private String productId;

    private String cartId;
}
