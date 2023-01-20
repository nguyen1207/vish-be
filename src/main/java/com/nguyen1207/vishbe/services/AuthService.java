package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.auth.ChangePasswordDto;

public interface AuthService {
    String changePassword(ChangePasswordDto changePasswordDto);
}
