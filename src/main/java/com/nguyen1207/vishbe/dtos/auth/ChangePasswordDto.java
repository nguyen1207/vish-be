package com.nguyen1207.vishbe.dtos.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChangePasswordDto {
    @NotNull(message = "User ID is required")
    private String userId;

    @NotNull(message = "Password is required")
    @Length(min = 6, message = "Password must have at least 6 characters")
    private String newPassword;
}
