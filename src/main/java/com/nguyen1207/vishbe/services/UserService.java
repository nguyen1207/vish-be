package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.address.CreateAddressDto;
import com.nguyen1207.vishbe.dtos.address.UpdateAddressDto;
import com.nguyen1207.vishbe.dtos.auth.RegisterDto;
import com.nguyen1207.vishbe.dtos.user.UpdatePersonalInfoDto;
import com.nguyen1207.vishbe.entities.user.Address;
import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.projections.user.PersonalInfo;

import java.util.List;

public interface UserService {
    User findUserById(String userId);

    void createUser(RegisterDto registerDto);

    PersonalInfo findPersonalInfoById(String userId);

    void updatePersonalInfo(String userId, UpdatePersonalInfoDto updatePersonalInfoDto);

    String createAddress(String userId, CreateAddressDto createAddressDto);

    List<Address> getAddresses(String userId);

    void updateAddress(String userId, UpdateAddressDto updateAddressDto);

    void deleteAddress(String userId, String addressId);

    void save(User user);
}
