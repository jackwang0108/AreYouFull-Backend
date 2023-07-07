package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.service.ShopService;
import com.ayf.areyoufull.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/{merchantID}")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
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

    @GetMapping("orders/hostory/cancelled")
    public Result cencelledOrders(){
        return Result.ok();
    }

    @PostMapping("/merchandise/publish")
    public Result publish(@PathVariable Integer merchantID, @RequestBody List<Merchandise> merchandises){
        Integer id = IDGenerator.getMerchandiseNextID();
        Integer shopID = shopService.getShopByMerchantID(merchantID).getShopID();
        for (Merchandise merchandise : merchandises) {
            merchandise.setMerchandiseID(id++);
            merchandise.setShopID(shopID);
            merchandise.setMerchandiseStatus((byte) 0);
            merchandise.setMerchandiseImgPath(Merchandise.defaultImgPath);
        }
        shopService.publish(merchandises);
        return Result.ok("上架商品成功");
    }

    @PostMapping("/merchandise/remove")
    public Result remove(@RequestBody List<Integer> merchandiseIDs){
        shopService.remove(merchandiseIDs);
        return Result.ok("下架商品成功");
    }

    @GetMapping("/home")
    public Result homePage(@PathVariable Integer merchantID){
        return preview(merchantID);
    }

    @GetMapping("/preview")
    public Result preview(@PathVariable Integer merchantID){
        List<Merchandise> merchandises = shopService.getMerchandisesByMerchantID(merchantID);
        return Result.ok("获取成功", merchandises);
    }

    @GetMapping("/info")
    public Result getInfo(@PathVariable Integer merchantID){
        Shop shop = shopService.getShopByMerchantID(merchantID);
        return Result.ok("获取成功", shop);
    }

    @PostMapping("/modify")
    public Result modifyShopInfo(Shop shop){
        shop.getAddress().setAddressID(IDGenerator.getAddressNextID());
        shop.getAccount().setPassword(DigestUtil.hmacSign(shop.getAccount().getPassword()));
        shopService.modifyShopInfo(shop);
        return Result.ok("更新成功");
    }

    @DeleteMapping("/terminate")
    public Result terminateShop(Shop shop){
        shopService.terminateShop(shop);
        return Result.ok("注销成功");
    }
}
