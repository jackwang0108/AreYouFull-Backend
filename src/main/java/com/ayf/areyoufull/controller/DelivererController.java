package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.service.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliverer/{delivererID}")
public class DelivererController {
    private final DelivererService delivererService;

    @Autowired
    public DelivererController(DelivererService delivererService) {
        this.delivererService = delivererService;
    }

    @GetMapping("/orders/available")
    public Result availableOrders(){
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

    @PostMapping("/orders/accepting")
    public Result accpetOrders(){
        return Result.ok();
    }

    @PostMapping("/orders/cancelling")
    public Result cancelOrders(){
        return Result.ok();
    }

    @PostMapping("/orders/finishing")
    public Result finishOrders(){
        return Result.ok();
    }

    @GetMapping("/home")
    public Result homePage(@PathVariable Integer userID){
        return Result.ok();
    }

    @GetMapping("/info")
    public Result getInfo(@PathVariable Integer delivererID){
        return Result.ok();
    }

    @PostMapping("/modify")
    public Result modifyInfo(Deliverer deliverer){
        return Result.ok();
    }

    @DeleteMapping("/terminate")
    public Result terminateAccount(Deliverer deliverer){
        return Result.ok();
    }
}
