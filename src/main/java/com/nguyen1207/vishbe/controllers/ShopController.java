package com.nguyen1207.vishbe.controllers;

import com.nguyen1207.vishbe.dtos.response.SuccessResponseDto;
import com.nguyen1207.vishbe.dtos.shop.CreateShopDto;
import com.nguyen1207.vishbe.dtos.shop.UpdateShopDto;
import com.nguyen1207.vishbe.services.ShopService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public SuccessResponseDto<Map<String, String>> createShop(@RequestBody @Valid CreateShopDto createShopDto) {
        String shopId = shopService.createShop(createShopDto);

        Map<String, String> data = new HashMap<>();

        data.put("shopId", shopId);

        return new SuccessResponseDto<>(data);
    }

    @PutMapping
    public SuccessResponseDto<Map<String, String>> updateShop(@RequestBody @Valid UpdateShopDto updateShopDto) {
        String shopId = shopService.updateShop(updateShopDto);

        Map<String, String> data = new HashMap<>();

        data.put("shopId", shopId);

        return new SuccessResponseDto<>(data);
    }
}
