package com.nguyen1207.vishbe.dtos.address;

import com.nguyen1207.vishbe.annotations.ValidateProvince;
import com.nguyen1207.vishbe.annotations.ValidateVietnamesePhone;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateAddressDto {
    @NotNull(message = "Address recipient name is required")
    @Length(min = 3, message = "Invalid name")
    private String fullName;

    @NotNull(message = "Phone is required")
    @ValidateVietnamesePhone
    private String phone;

    @NotNull(message = "Province is required")
    @ValidateProvince
    private String province;

    @NotNull(message = "Address details is required")
    private String details;

    @NotNull(message = "Is default address is required")
    private Boolean isDefault;
}
