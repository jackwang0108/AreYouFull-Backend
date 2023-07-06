package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.*;
import com.ayf.areyoufull.service.*;
import com.ayf.areyoufull.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping("/register")
    public Result register(@RequestBody RegisterAccount registerAccount){
        Integer nextID = IDService.getNextAccountID();
        Account account = new Account();
        account.setAccountID(nextID);
        account.setPassword(DigestUtil.hmacSign(registerAccount.getPassword()));
        account.setNickname(registerAccount.getNickname());
        account.setAccountPhone(registerAccount.getAccountPhone());
        account.setEmail(registerAccount.getEmail());
        account.setAvatar(Account.defaultAvatar);
        switch (registerAccount.getType()){
            case 0 -> {
                User user = new User();
                user.setUserID(nextID);
                user.setAccount(account);
                userService.newUser(user);
            }
            case 1 -> {
                Deliverer deliverer = new Deliverer();
                deliverer.setDelivererID(nextID);
                deliverer.setAccount(account);
                delivererService.newDeliverer(deliverer);
            }
            case 2 -> {
                Shop shop = new Shop();
                shop.setMerchantID(nextID);
                shop.setAccount(account);
                shopService.newShop(shop);
            }
            default -> Result.err(Result.CODE_ERR_BUSINESS, "不支持的用户类型");
        }
        return Result.ok("注册成功", nextID);
    }
}
