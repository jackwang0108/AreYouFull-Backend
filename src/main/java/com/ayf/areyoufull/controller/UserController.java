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

    @PostMapping("/ordering")
    public Result newOrder(Order order){
        return Result.ok();
    }

    @PostMapping("/paying")
    public Result pay(){
        return Result.ok();
    }

    @GetMapping("/orders/unfinished")
    public Result unfinishedOrders(){
        return Result.ok();
    }

    @GetMapping("/orders/finished")
    public Result finishedOrders(){
        return Result.ok();
    }

    @GetMapping("/orders/cancelled")
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
