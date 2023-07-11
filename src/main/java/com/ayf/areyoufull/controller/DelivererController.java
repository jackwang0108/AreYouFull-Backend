package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.service.DelivererService;
import com.ayf.areyoufull.utils.DigestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deliverer/{delivererID}")
public class DelivererController {
    private final DelivererService delivererService;
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        DelivererController.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public DelivererController(DelivererService delivererService) {
        this.delivererService = delivererService;
    }


    @PostMapping("/position/update")
    public Result updatePosition(@RequestBody Map<String, Integer> position, @PathVariable Integer delivererID){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pos = objectMapper.writeValueAsString(position);
            stringRedisTemplate.opsForValue().set(String.valueOf(delivererID), pos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.ok("更新成功");
    }

    @GetMapping("/position/draw")
    public Result getPosition(@PathVariable Integer delivererID){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map pos = objectMapper.readValue(stringRedisTemplate.opsForValue().get(String.valueOf(delivererID)), Map.class);
            return Result.ok("获取成功", pos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "业务错误");
    }

    @GetMapping("/orders/available")
    public Result availableOrders(){
        Order order = new Order();
        order.setStatus(Order.ORDER_MERCHANT_FINISHED);
        List<Order> orders = delivererService.queryOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/unfinished")
    public Result unfinishedOrders(){
        Order order = new Order();
        order.setStatus(Order.ORDER_DELIVERER_GOT);
        List<Order> orders = delivererService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @PostMapping("/orders/accepting")
    public Result accepting(@RequestBody Order order){
        order.setStatus(Order.ORDER_DELIVERER_GOT);
        delivererService.updateOrder(order);
        return Result.ok("接单成功");
    }

    @PostMapping("/orders/cancelling")
    public Result cancelling(@RequestBody Order order){
        order.setStatus(Order.ORDER_CANCELLED);
        delivererService.updateOrder(order);
        return Result.ok("取消成功");
    }

    @PostMapping("/orders/finishing")
    public Result finishing(@RequestBody Order order){
        order.setStatus(Order.ORDER_FINISHED);
        delivererService.updateOrder(order);
        return Result.ok("结束成功");
    }

    @GetMapping("/orders/history/finished")
    public Result finishedOrders(@PathVariable Integer delivererID){
        Order order = new Order();
        order.setDelivererID(delivererID);
        order.setStatus(Order.ORDER_FINISHED);
        List<Order> orders = delivererService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/history/cancelled")
    public Result cancelledOrders(@PathVariable Integer delivererID){
        Order order = new Order();
        order.setDelivererID(delivererID);
        order.setStatus(Order.ORDER_CANCELLED);
        List<Order> orders = delivererService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
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
    public Result modifyInfo(@RequestBody Deliverer deliverer){
        deliverer.getAccount().setPassword(DigestUtil.hmacSign(deliverer.getAccount().getPassword()));
        delivererService.modifyDelivererInfo(deliverer);
        return Result.ok("更新成功");
    }

    @DeleteMapping("/terminate")
    public Result terminateAccount(Deliverer deliverer){
        delivererService.terminateByDeliverer(deliverer);
        return Result.ok();
    }
}
