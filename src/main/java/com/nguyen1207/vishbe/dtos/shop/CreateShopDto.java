package com.nguyen1207.vishbe.dtos.shop;

import com.nguyen1207.vishbe.dtos.address.CreateAddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateShopDto {
    @NotNull(message = "User ID is required")
    private String userId;

    @NotNull(message = "Shop address is required")
    @Valid
    @ToString.Exclude
    private CreateAddressDto address;

    @NotNull(message = "Shop name is required")
    @Length(min = 3, message = "Invalid shop name")
    private String name;

    @NotNull(message = "Avatar is required")
    private String avatarUrl;

    @NotNull(message = "Description is required")
    private String description;
}
