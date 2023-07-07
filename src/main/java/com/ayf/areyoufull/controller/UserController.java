package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.entity.*;
import com.ayf.areyoufull.service.UserService;
import com.ayf.areyoufull.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{userID}")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/browse/shop")
    public Result getShops(@RequestBody Map<String, Integer> body){
        List<Shop> shops = userService.browseShops(body.get("amount"));
        return Result.ok("获取成功", shops);
    }

    @PostMapping("/browse/merchandise")
    public Result getMerchandises(@RequestBody Map<String, Integer> body){
        List<Merchandise> merchandises = userService.browseMerchandises(body.get("shopID"));
        return Result.ok("获取成功", merchandises);
    }

    @PostMapping("/orders/ordering")
    public Result ordering(@RequestBody Order order){
        Integer orderID = userService.createOrder(order);
        return Result.ok("创建成功", orderID);
    }

    @PostMapping("/orders/paying")
    public Result paying(@RequestBody Map<String, Date> body){
        Date payTime = body.get("payTime");

        return Result.ok();
    }

    @PostMapping("/orders/cancelling")
    public Result cancelling(){
        return Result.ok();
    }

    @GetMapping("/orders/waiting")
    public Result waiting(){
        return Result.ok();
    }

    @GetMapping("/orders/history/finished")
    public Result finishedOrders(){
        return Result.ok();
    }

    @GetMapping("/orders/history/cancelled")
    public Result cancelledOrders(){
        return Result.ok();
    }

    @GetMapping("/home")
    public Result homePage(){
        List<Shop> shops = userService.browseShops(20);
        return Result.ok("获取成功", shops);
    }

    @GetMapping("/info")
    public Result getInfo(@PathVariable Integer userID){
        User user = userService.getUserByUserID(userID);
        return Result.ok("获取成功", user);
    }

    @PostMapping("/modify")
    public Result modifyInfo(@RequestBody User user){
        Integer nextID = IDGenerator.getNextAddressID();
        for (Address address : user.getAddresses()) {
            if (address.getAddressID() == null)
                address.setAddressID(nextID++);
        }
        user.getAccount().setPassword(DigestUtil.hmacSign(user.getAccount().getPassword()));
        userService.modifyUserInfo(user);
        return Result.ok("更新成功");
    }

    @DeleteMapping("/terminate")
    public Result terminateUser(@RequestBody User user){
        userService.terminateByUser(user);
        return Result.ok("注销成功");
    }
}
