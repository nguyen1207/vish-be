package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.shop.CreateShopDto;
import com.nguyen1207.vishbe.dtos.shop.UpdateShopDto;
import com.nguyen1207.vishbe.entities.shop.Shop;

public interface ShopService {
    Shop findShopByName(String name);

    Shop findShopById(String shopId);

    boolean isShopExist(String shopName);

    String createShop(CreateShopDto createShopDto);

    String updateShop(UpdateShopDto updateShopDto);
}
