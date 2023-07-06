package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/{shopID}")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/new-merchandise")
    public Result publish(@PathVariable Integer shopID, @RequestBody List<Merchandise> merchandises){
        Integer id = IDGenerator.getMerchandiseNextID();
        for (Merchandise merchandise : merchandises) {
            merchandise.setMerchandiseID(id++);
            merchandise.setShopID(shopID);
            merchandise.setMerchandiseStatus((byte) 0);
            merchandise.setMerchandiseImgPath(Merchandise.defaultImgPath);
        }
        shopService.publish(merchandises);
        return Result.ok("上架商品成功");
    }

    @GetMapping("/merchandises")
    public Result show(@PathVariable Integer shopID){
        List<Merchandise> merchandises = shopService.getMerchandiseOfShop(shopID);
        return Result.ok("获取成功", merchandises);
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
