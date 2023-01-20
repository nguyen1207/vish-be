package com.nguyen1207.vishbe.controllers;

import com.nguyen1207.vishbe.dtos.address.CreateAddressDto;
import com.nguyen1207.vishbe.dtos.address.DeleteAddressDto;
import com.nguyen1207.vishbe.dtos.address.UpdateAddressDto;
import com.nguyen1207.vishbe.dtos.response.SuccessResponseDto;
import com.nguyen1207.vishbe.dtos.user.UpdatePersonalInfoDto;
import com.nguyen1207.vishbe.entities.user.Address;
import com.nguyen1207.vishbe.projections.user.PersonalInfo;
import com.nguyen1207.vishbe.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public SuccessResponseDto<PersonalInfo> getUser(@PathVariable String userId) {
        PersonalInfo userInfo = userService.findPersonalInfoById(userId);

        return new SuccessResponseDto<>(userInfo);
    }

    @PutMapping("/{userId}")
    public SuccessResponseDto<Void> updateInfo(@PathVariable String userId, @RequestBody @Valid UpdatePersonalInfoDto updatePersonalInfoDto) {
        userService.updatePersonalInfo(userId, updatePersonalInfoDto);

        return new SuccessResponseDto<>();
    }

    @PostMapping("/addresses/{userId}")
    public SuccessResponseDto<Map<String, String>> createAddress(@PathVariable String userId, @RequestBody @Valid CreateAddressDto createAddressDto) {
        String addressId = userService.createAddress(userId, createAddressDto);

        Map<String, String> data = new HashMap<>();

        data.put("addressId", addressId);

        return new SuccessResponseDto<>(data);
    }


    @GetMapping("/addresses/{userId}")
    public SuccessResponseDto<List<Address>> getAddresses(@PathVariable String userId) {
        List<Address> addresses = userService.getAddresses(userId);

        return new SuccessResponseDto<>(addresses);
    }

    @PutMapping("/addresses/{userId}")
    public SuccessResponseDto<Void> updateAddress(@PathVariable String userId, @RequestBody @Valid UpdateAddressDto updateAddressDto) {
        userService.updateAddress(userId, updateAddressDto);

        return new SuccessResponseDto<>();
    }

    @DeleteMapping("/addresses/{userId}")
    public SuccessResponseDto<Void> deleteAddress(@PathVariable String userId, @RequestBody @Valid DeleteAddressDto deleteAddressDto) {
        userService.deleteAddress(userId, deleteAddressDto.getAddressId());

        return new SuccessResponseDto<>();
    }
}
