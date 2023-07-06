package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.Result;
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

    @PostMapping("/orders/accepting")
    public Result accepting(){
        return Result.ok();
    }

    @PostMapping("/orders/cancelling")
    public Result cancelling(){
        return Result.ok();
    }

    @PostMapping("/orders/finishing")
    public Result finishing(){
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
    public Result homePage(@PathVariable Integer delivererID){
        return Result.ok("获取成功", "没有主页");
    }

    @GetMapping("/info")
    public Result getInfo(@PathVariable Integer delivererID){
        Deliverer deliverer = delivererService.getDelivererByDelivererID(delivererID);
        return Result.ok("获取成功", deliverer);
    }

    @PostMapping("/modify")
    public Result modifyInfo(Deliverer deliverer){
        delivererService.modifyDelivererInfo(deliverer);
        return Result.ok("");
    }

    @DeleteMapping("/terminate")
    public Result terminateAccount(Deliverer deliverer){
        delivererService.terminateByDeliverer(deliverer);
        return Result.ok();
    }
}
