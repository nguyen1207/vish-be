package com.nguyen1207.vishbe.dtos.user;

import com.nguyen1207.vishbe.annotations.ValidateVietnamesePhone;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdatePersonalInfoDto {
    private String fullName;

    @Email(message = "Invalid email")
    private String email;

    @ValidateVietnamesePhone
    private String phone;

    private String avatarUrl;
}
