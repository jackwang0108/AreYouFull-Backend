package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userID}")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/browse/shop")
    public Result shops(){
        return Result.ok();
    }

    @GetMapping("/browse/merchandise")
    public Result merchandises(){
        return Result.ok();
    }

    @PostMapping("/orders/ordering")
    public Result ordering(Order order){
        return Result.ok();
    }

    @PostMapping("/orders/paying")
    public Result paying(){
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
    public Result homePage(@PathVariable Integer userID){
        return Result.ok();
    }

    @GetMapping("/info")
    public Result getInfo(@PathVariable Integer userID){
        return Result.ok();
    }

    @PostMapping("/modify")
    public Result modifyInfo(User user){
        return Result.ok();
    }

    @DeleteMapping("/terminate")
    public Result terminateAccount(User user){
        return Result.ok();
    }
}
