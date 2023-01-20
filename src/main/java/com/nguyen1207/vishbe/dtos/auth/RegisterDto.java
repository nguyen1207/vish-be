package com.nguyen1207.vishbe.dtos.auth;

import com.nguyen1207.vishbe.annotations.ValidateVietnamesePhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterDto {
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Full name is required")
    @Length(min = 3, message = "Invalid name")
    private String fullName;

    @NotNull(message = "Phone is required")
    @ValidateVietnamesePhone
    private String phone;

    @NotNull(message = "Password is required")
    @Length(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
