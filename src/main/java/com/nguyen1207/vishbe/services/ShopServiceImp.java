package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.address.CreateAddressDto;
import com.nguyen1207.vishbe.dtos.address.UpdateAddressDto;
import com.nguyen1207.vishbe.dtos.shop.CreateShopDto;
import com.nguyen1207.vishbe.dtos.shop.UpdateShopDto;
import com.nguyen1207.vishbe.entities.shop.Shop;
import com.nguyen1207.vishbe.entities.shop.ShopAddress;
import com.nguyen1207.vishbe.entities.user.Address;
import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.exceptions.BadRequestException;
import com.nguyen1207.vishbe.exceptions.NotFoundException;
import com.nguyen1207.vishbe.repositories.ShopRepository;
import com.nguyen1207.vishbe.utils.MapUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImp implements ShopService {
    private final ShopRepository shopRepository;

    private final UserService userService;

    public ShopServiceImp(ShopRepository shopRepository, UserService userService) {
        this.shopRepository = shopRepository;
        this.userService = userService;
    }

    @Override
    public Shop findShopByName(String name) {
        Optional<Shop> shopOpt = shopRepository.findByName(name);

        if (shopOpt.isEmpty())
            throw new NotFoundException("Not found shop with name" + name);

        return shopOpt.get();
    }

    @Override
    public Shop findShopById(String shopId) {
        Optional<Shop> shopOpt = shopRepository.findById(shopId);

        if (shopOpt.isEmpty())
            throw new NotFoundException("Not found shop ID" + shopId);

        return shopOpt.get();
    }

    @Override
    public boolean isShopExist(String shopName) {
        Optional<Shop> shopOpt = shopRepository.findByName(shopName);

        return shopOpt.isPresent();
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public String createShop(CreateShopDto createShopDto) {
        User user = userService.findUserById(createShopDto.getUserId());

        if (user.getShop() != null)
            throw new BadRequestException("This user has owned a shop");

        if (isShopExist(createShopDto.getName()))
            throw new BadRequestException("This shop name has existed");

        CreateAddressDto createAddressDto = createShopDto.getAddress();
        String[] provinceSplit = createAddressDto.getProvince().split(",");

        String city = provinceSplit[0].trim();
        String district = provinceSplit[1].trim();
        String ward = provinceSplit[2].trim();

        Address address = Address.builder()
                .fullName(createAddressDto.getFullName())
                .phone(createAddressDto.getPhone())
                .city(city)
                .district(district)
                .ward(ward)
                .details(createAddressDto.getDetails())
                .isDefault(true)
                .build();


        Shop shop = Shop.builder()
                .owner(user)
                .name(createShopDto.getName())
                .avatarUrl(createShopDto.getAvatarUrl())
                .description(createShopDto.getDescription())
                .build();

        Shop savedShop = shopRepository.save(shop);

        ShopAddress shopAddress = ShopAddress.builder()
                .address(address)
                .shop(savedShop)
                .build();

        shop.setAddress(shopAddress);

        shopRepository.save(shop);

        return savedShop.getShopId();
    }

    @Override
    public String updateShop(UpdateShopDto updateShopDto) {
        User user = userService.findUserById(updateShopDto.getUserId());

        Shop shop = user.getShop();

        if (shop == null)
            throw new NotFoundException("Not found shop");

        MapUtil.copyNonNullProperties(updateShopDto, shop);

        UpdateAddressDto updateAddressDto = updateShopDto.getAddress();

        if (updateAddressDto != null) {
            String[] provinceSplit = updateAddressDto.getProvince().split(",");

            String city = provinceSplit[0].trim();
            String district = provinceSplit[1].trim();
            String ward = provinceSplit[2].trim();


            Address address = shop.getAddress().getAddress();
            address.setCity(city);
            address.setDistrict(district);
            address.setWard(ward);

            MapUtil.copyNonNullProperties(updateAddressDto, address, "addressId");
        }

        shopRepository.save(shop);

        return shop.getShopId();
    }
}
