package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.service.ShopService;
import com.ayf.areyoufull.utils.DigestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop/{merchantID}")
public class ShopController {
    private final ShopService shopService;
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        ShopController.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/position/update")
    public Result updatePosition(@RequestBody Map<String, Integer> position, @PathVariable Integer merchantID){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pos = objectMapper.writeValueAsString(position);
            stringRedisTemplate.opsForValue().set(String.valueOf(merchantID), pos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.ok("更新成功");
    }

    @GetMapping("/orders/available")
    public Result availableOrders(){
        Order order = new Order();
        order.setStatus(Order.ORDER_PAYED);
        List<Order> orders = shopService.queryOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/unfinished")
    public Result unfinishedOrders(){
        Order order = new Order();
        order.setStatus(Order.ORDER_MERCHANT_ASSURED);
        List<Order> orders = shopService.queryOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @PostMapping("/orders/accepting")
    public Result accepting(@RequestBody Order order){
        order.setStatus(Order.ORDER_MERCHANT_ASSURED);
        shopService.updateOrder(order);
        return Result.ok("接单成功");
    }

    @PostMapping("/orders/cancelling")
    public Result cancelling(@RequestBody Order order){
        order.setStatus(Order.ORDER_CANCELLED);
        shopService.updateOrder(order);
        return Result.ok("取消成功");
    }

    @PostMapping("/orders/finishing")
    public Result finishing(@RequestBody Order order){
        order.setStatus(Order.ORDER_MERCHANT_FINISHED);
        shopService.updateOrder(order);
        return Result.ok("完成成功");
    }

    @GetMapping("/orders/history/finished")
    public Result finishedOrders(@PathVariable Integer merchantID){
        Order order = new Order();
        order.setShopID(merchantID);
        order.setStatus(Order.ORDER_FINISHED);
        shopService.querySelfOrderByStatus(order);
        return Result.ok();
    }

    @GetMapping("orders/history/cancelled")
    public Result cancelledOrders(@PathVariable Integer merchantID){
        Order order = new Order();
        order.setShopID(merchantID);
        order.setStatus(Order.ORDER_CANCELLED);
        shopService.querySelfOrderByStatus(order);
        return Result.ok();
    }

    @PostMapping("/merchandise/publish")
    public Result publish(@PathVariable Integer merchantID, @RequestBody List<Merchandise> merchandises){
        Integer id = IDGenerator.getNextMerchandiseID();
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

    @GetMapping("/preview")
    public Result preview(@PathVariable Integer merchantID){
        List<Merchandise> merchandises = shopService.getMerchandisesByMerchantID(merchantID);
        return Result.ok("获取成功", merchandises);
    }

    @GetMapping("/home")
    public Result homePage(@PathVariable Integer merchantID){
        return preview(merchantID);
    }

    @GetMapping("/info")
    public Result getInfo(@PathVariable Integer merchantID){
        Shop shop = shopService.getShopByMerchantID(merchantID);
        return Result.ok("获取成功", shop);
    }

    @PostMapping("/modify")
    public Result modifyShopInfo(@RequestBody Shop shop){
        shop.getAccount().setPassword(DigestUtil.hmacSign(shop.getAccount().getPassword()));
        shop.getAddress().get(0).setAddressID(IDGenerator.getNextAddressID());
        shopService.modifyShopInfo(shop);
        return Result.ok("更新成功");
    }

    @DeleteMapping("/terminate")
    public Result terminateShop(Shop shop){
        shopService.terminateShop(shop);
        return Result.ok("注销成功");
    }
}
