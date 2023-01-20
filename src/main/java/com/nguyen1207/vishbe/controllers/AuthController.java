package com.nguyen1207.vishbe.controllers;

import com.nguyen1207.vishbe.dtos.auth.ChangePasswordDto;
import com.nguyen1207.vishbe.dtos.auth.RegisterDto;
import com.nguyen1207.vishbe.dtos.response.SuccessResponseDto;
import com.nguyen1207.vishbe.services.AuthService;
import com.nguyen1207.vishbe.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public SuccessResponseDto<Void> register(@RequestBody @Valid RegisterDto registerDto) {
        userService.createUser(registerDto);

        return new SuccessResponseDto<>();
    }

    @PutMapping("/change-password")
    public SuccessResponseDto<Map<String, String>> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        String userId = authService.changePassword(changePasswordDto);

        Map<String, String> data = new HashMap<>();

        data.put("userId", userId);

        return new SuccessResponseDto<>(data);
    }
}
