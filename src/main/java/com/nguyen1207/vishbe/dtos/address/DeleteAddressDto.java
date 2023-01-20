package com.nguyen1207.vishbe.dtos.address;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteAddressDto {
    @NotNull(message = "Address ID is required")
    private String addressId;
}
