package com.ayf.areyoufull.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.entity.*;
import com.ayf.areyoufull.service.UserService;
import com.ayf.areyoufull.utils.AlipayUtil;
import com.ayf.areyoufull.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{userID}")
public class UserController {
    private final UserService userService;
    private final AlipayUtil alipayUtil;

    @Autowired
    public UserController(UserService userService, AlipayUtil alipayUtil) {
        this.userService = userService;
        this.alipayUtil = alipayUtil;
    }

    @PostMapping("/position")
    public Result realTimePosition(@RequestBody Integer integer){
        return Result.ok();
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
        Integer nextOrderID = IDGenerator.getNextOrderID();
        order.setOrderID(nextOrderID);
        order.setStatus(Order.ORDER_CREATED);
        order.getOrderDetail().forEach(orderDetail -> orderDetail.setOrderID(nextOrderID));
        userService.createOrder(order);
        return Result.ok("创建成功", nextOrderID);
    }

    @PostMapping("/orders/paying")
    public Result paying(@RequestBody Order order){
        AlipayClient alipayClient = alipayUtil.getAlipayClient();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl("http://localhost:8848/returnUrl");
        alipayRequest.setBizContent("{" +
                "orderID: " + order.getOrderID() +
                "}"
        );
        String body = null;
        try {
            body = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        order.setStatus(Order.ORDER_PAYED);
        userService.updateOrder(order);
        return Result.ok("支付成功");
    }

    @PostMapping("/orders/cancelling")
    public Result cancelling(@RequestBody Order order){
        AlipayClient alipayClient = alipayUtil.getAlipayClient();
        AlipayTradeCancelRequest alipayRequest = new AlipayTradeCancelRequest();
        alipayRequest.setBizContent("{" +
                "orderID: " + order.getOrderID() +
                "}"
        );
        String body = null;
        try {
            body = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        order.setStatus(Order.ORDER_CANCELLED);
        userService.updateOrder(order);
        return Result.ok("取消成功");
    }

    @GetMapping("/orders/waiting")
    public Result waiting(@PathVariable Integer userID){
        Order order = new Order();
        order.setUserID(userID);
        order.setStatus((byte) 4);
        List<Order> orders = userService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/history/finished")
    public Result finishedOrders(@PathVariable Integer userID){
        Order order = new Order();
        order.setUserID(userID);
        order.setStatus((byte) 5);
        List<Order> orders = userService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/history/cancelled")
    public Result cancelledOrders(@PathVariable Integer userID){
        Order order = new Order();
        order.setUserID(userID);
        order.setStatus((byte) 6);
        List<Order> orders = userService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
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
