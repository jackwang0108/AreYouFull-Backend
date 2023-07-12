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

import java.util.ArrayList;
import java.util.HashMap;
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
            return Result.ok("获取成功", getCurrPos());
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

    public static List<Map<String, Double>> getPos(){
        Double[] lng = {
                34.29381, 34.293746, 34.293634, 34.293591, 34.293591, 34.293516,
                34.293233, 34.293142, 34.292897, 34.292737, 34.29279, 34.292779,
                34.292774, 34.292753, 34.292758, 34.292742, 34.292737, 34.292838,
                34.292993, 34.293126, 34.293249, 34.293356, 34.293489, 34.293489
        };
        Double[] lat = {
                108.936074, 108.936061, 108.936087, 108.936074, 108.936035, 108.93608,
                108.936061, 108.936067, 108.936061, 108.936061, 108.935751, 108.935596,
                108.93524, 108.934949, 108.934801, 108.934187, 108.934135, 108.933915,
                108.933935, 108.933941, 108.933928, 108.933922, 108.934057, 108.934057
        };
        ArrayList<Map<String, Double>> posList = new ArrayList<>();
        for (int i = 0; i < lng.length; ++i){
            Map<String, Double> pos = new HashMap<>();
            pos.put("lng", lng[i]);
            pos.put("lat", lat[i]);
            posList.add(pos);
        }
        return posList;
    }
    private static int cnt = 0;
    private static Map<String, Double>[] getCurrPos(){
        List<Map<String, Double>> userPos = UserController.getPos();
        List<Map<String, Double>> shopPos = ShopController.getPos();
        List<Map<String, Double>> delivererPos = DelivererController.getPos();
        Map[] maps = {userPos.get(0), shopPos.get(0), delivererPos.get(cnt++)};
        return maps;
    }
}
