package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.RegisterAccount;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.service.DelivererService;
import com.ayf.areyoufull.service.ShopService;
import com.ayf.areyoufull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final DelivererService delivererService;
    private final ShopService shopService;

    @Autowired
    public RegisterController(UserService userService, DelivererService delivererService, ShopService shopService) {
        this.userService = userService;
        this.delivererService = delivererService;
        this.shopService = shopService;
    }

    public Result register(RegisterAccount registerAccount){
        switch (registerAccount.getType()){
            case 0 -> {

            }
            case 1 -> {

            }
            case 2 -> {

            }
            default -> {

            }
        }
        return Result.ok();
    }
}
