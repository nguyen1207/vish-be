package com.nguyen1207.vishbe.dtos.address;

import com.nguyen1207.vishbe.annotations.ValidateProvince;
import com.nguyen1207.vishbe.annotations.ValidateVietnamesePhone;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateAddressDto {
    @NotNull(message = "Address ID is required")
    private String addressId;

    @Length(min = 3, message = "Invalid name")
    private String fullName;

    @ValidateVietnamesePhone
    private String phone;

    @ValidateProvince
    private String province;

    @Length(min = 3, message = "Invalid address details")
    private String details;

    @NotNull(message = "Is default address is required")
    private Boolean isDefault;
}
