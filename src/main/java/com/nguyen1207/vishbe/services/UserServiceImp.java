package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.address.CreateAddressDto;
import com.nguyen1207.vishbe.dtos.address.UpdateAddressDto;
import com.nguyen1207.vishbe.dtos.auth.RegisterDto;
import com.nguyen1207.vishbe.dtos.user.UpdatePersonalInfoDto;
import com.nguyen1207.vishbe.entities.user.Address;
import com.nguyen1207.vishbe.entities.user.Cart;
import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.entities.user.UserAddress;
import com.nguyen1207.vishbe.exceptions.BadRequestException;
import com.nguyen1207.vishbe.exceptions.NotFoundException;
import com.nguyen1207.vishbe.projections.user.PersonalInfo;
import com.nguyen1207.vishbe.repositories.AddressRepository;
import com.nguyen1207.vishbe.repositories.UserAddressRepository;
import com.nguyen1207.vishbe.repositories.UserRepository;
import com.nguyen1207.vishbe.utils.MapUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserAddressRepository userAddressRepository;

    public UserServiceImp(UserRepository userRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder,
                          UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public void createUser(RegisterDto registerDto) {
        Cart cart = new Cart();
        Optional<User> existedUserOpt = userRepository.findByEmailOrPhone(registerDto.getEmail(), registerDto.getPhone());

        if (existedUserOpt.isPresent()) {
            User existedUser = existedUserOpt.get();

            if (Objects.equals(existedUser.getEmail(), registerDto.getEmail()))
                throw new BadRequestException("Email has already been used");

            if (Objects.equals(existedUser.getPhone(), registerDto.getPhone()))
                throw new BadRequestException("This phone number has already been used");
        }

        String defaultAvtarUrl = "https://ideastest.org.uk/wp-content/uploads/2019/04/default-avatar-1.jpg";

        User user = User.builder()
                .cart(cart)
                .email(registerDto.getEmail())
                .fullName(registerDto.getFullName())
                .avatarUrl(defaultAvtarUrl)
                .phone(registerDto.getPhone())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    public PersonalInfo findPersonalInfoById(String userId) {
        Optional<PersonalInfo> userInfoOpt = userRepository.findUserPersonalInfo(userId);

        if (userInfoOpt.isEmpty())
            throw new NotFoundException("Not found user ID: " + userId);

        return userInfoOpt.get();
    }

    @Override
    public void updatePersonalInfo(String userId, UpdatePersonalInfoDto updatePersonalInfoDto) {
        Optional<User> userOpt = userRepository.findByUserId(userId);

        if (userOpt.isEmpty())
            throw new NotFoundException("Not found user ID: " + userId);

        User user = userOpt.get();

        MapUtil.copyNonNullProperties(updatePersonalInfoDto, user);

        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public String createAddress(String userId, CreateAddressDto createAddressDto) {
        User user = findUserById(userId);

        if (createAddressDto.getIsDefault()) {
            addressRepository.unSetDefaultAddress(userId);
        }

        String[] provinceSplit = createAddressDto.getProvince().split(",");

        String city = provinceSplit[0];
        String district = provinceSplit[1];
        String ward = provinceSplit[2];

        Address address = Address.builder()
                .fullName(createAddressDto.getFullName())
                .phone(createAddressDto.getPhone())
                .city(city)
                .district(district)
                .ward(ward)
                .details(createAddressDto.getDetails())
                .isDefault(createAddressDto.getIsDefault())
                .build();

        if (user.getAddresses().size() == 0) {
            address.setDefault(true);
        }

        UserAddress userAddress = UserAddress.builder()
                .user(user)
                .address(address)
                .build();

        userAddress = userAddressRepository.save(userAddress);

        return userAddress.getAddressId();
    }

    public List<Address> getAddresses(String userId) {
        User user = findUserById(userId);

        return user.getAddresses().stream()
                .map(UserAddress::getAddress)
                .toList();
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public void updateAddress(String userId, UpdateAddressDto updateAddressDto) {
        String addressId = updateAddressDto.getAddressId();

        User user = findUserById(userId);


        if (user.getAddresses().stream().noneMatch(userAddress ->
                userAddress.getAddressId().equals(addressId)))
            throw new NotFoundException("Not found address ID: " + addressId);


        List<UserAddress> userAddresses = user.getAddresses();

        for (UserAddress userAddress : userAddresses) {
            if (Objects.equals(userAddress.getAddressId(), addressId)) {
                Address address = userAddress.getAddress();

                MapUtil.copyNonNullProperties(updateAddressDto, address);

                if (updateAddressDto.getProvince() != null) {
                    String[] provinceSplit = updateAddressDto.getProvince().split(",");

                    String city = provinceSplit[0];
                    String district = provinceSplit[1];
                    String ward = provinceSplit[2];

                    address.setCity(city);
                    address.setDistrict(district);
                    address.setWard(ward);
                }

                if (updateAddressDto.getIsDefault() && !address.isDefault()) {
                    addressRepository.unSetDefaultAddress(userId);
                }

                if (updateAddressDto.getIsDefault()) {
                    address.setDefault(true);
                }

                addressRepository.save(address);

                break;
            }
        }
    }

    @Override
    public void deleteAddress(String userId, String addressId) {
        User user = findUserById(userId);

        Optional<UserAddress> userAddressOpt = userAddressRepository.findById(addressId);

        if (userAddressOpt.isEmpty())
            throw new NotFoundException("Not found address ID: " + addressId);

        UserAddress userAddress = userAddressOpt.get();

        if (!Objects.equals(userAddress.getUser().getUserId(), userId))
            throw new NotFoundException("Not found address ID: " + addressId);

        user.getAddresses().remove(userAddress);

        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(String userId) {
        Optional<User> userOpt = userRepository.findByUserId(userId);

        if (userOpt.isEmpty())
            throw new NotFoundException("Not found user ID: " + userId);

        return userOpt.get();
    }
}
