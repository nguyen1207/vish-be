package com.nguyen1207.vishbe.dtos.shop;

import com.nguyen1207.vishbe.dtos.address.UpdateAddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateShopDto {
    @NotNull(message = "User ID is required")
    private String userId;

    @Valid
    @ToString.Exclude
    private UpdateAddressDto address;

    @Length(min = 3, message = "Invalid shop name")
    private String name;

    private String avatarUrl;

    private String description;
}
