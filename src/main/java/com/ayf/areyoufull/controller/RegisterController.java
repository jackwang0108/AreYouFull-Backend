package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.entity.*;
import com.ayf.areyoufull.service.DelivererService;
import com.ayf.areyoufull.service.ShopService;
import com.ayf.areyoufull.service.UserService;
import com.ayf.areyoufull.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/user")
    public Result register(@RequestBody User user){
        Integer nextAccountID = IDGenerator.getNextAccountID();
        user.setUserID(nextAccountID);
        Account account = user.getAccount();
        account.setAccountID(nextAccountID);
        account.setPassword(DigestUtil.hmacSign(account.getPassword()));
        account.setAvatar(Account.defaultAvatar);
        account.setEmail(Account.defaultEmail);
        Integer nextAddressID = IDGenerator.getNextAddressID();
        List<Address> addresses = user.getAddresses();
        for (Address address : addresses) {
            address.setAddressID(nextAddressID++);
            address.setAccountID(nextAccountID);
        }
        userService.newUser(user);
        return Result.ok("注册成功", nextAccountID);
    }

    @PostMapping("/deliverer")
    public Result register(@RequestBody Deliverer deliverer){
        Integer nextAccountID = IDGenerator.getNextAccountID();
        deliverer.setDelivererID(nextAccountID);
        Account account = deliverer.getAccount();
        account.setAccountID(nextAccountID);
        account.setPassword(DigestUtil.hmacSign(account.getPassword()));
        account.setAvatar(Account.defaultAvatar);
        account.setEmail(Account.defaultEmail);
        delivererService.newDeliverer(deliverer);
        return Result.ok("注册成功", nextAccountID);
    }

    @PostMapping("/shop")
    public Result register(@RequestBody Shop shop){
        Integer nextAccountID = IDGenerator.getNextAccountID();
        shop.setShopID(IDGenerator.getNextShopID());
        shop.setMerchantID(nextAccountID);
        Account account = shop.getAccount();
        account.setAccountID(nextAccountID);
        account.setPassword(DigestUtil.hmacSign(account.getPassword()));
        account.setAvatar(Account.defaultAvatar);
        account.setEmail(Account.defaultEmail);
        Integer nextAddressID = IDGenerator.getNextAddressID();
        Address address = shop.getAddress();
        address.setAddressID(nextAddressID);
        address.setAccountID(nextAccountID);
        shopService.newShop(shop);
        return Result.ok("注册成功", nextAccountID);
    }
}
