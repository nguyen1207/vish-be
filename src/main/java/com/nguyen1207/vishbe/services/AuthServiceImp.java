package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.auth.ChangePasswordDto;
import com.nguyen1207.vishbe.entities.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthServiceImp(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public String changePassword(ChangePasswordDto changePasswordDto) {
        String userId = changePasswordDto.getUserId();
        String newPassword = changePasswordDto.getNewPassword();

        User user = userService.findUserById(userId);

        user.setPassword(passwordEncoder.encode(newPassword));

        userService.save(user);

        return user.getUserId();
    }
}
