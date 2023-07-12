package com.ayf.areyoufull.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ayf.areyoufull.dao.IDGenerator;
import com.ayf.areyoufull.entity.*;
import com.ayf.areyoufull.service.UserService;
import com.ayf.areyoufull.utils.AlipayUtil;
import com.ayf.areyoufull.utils.Constant;
import com.ayf.areyoufull.utils.DigestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/user/{userID}")
public class UserController {
    private final UserService userService;
    private final AlipayUtil alipayUtil;
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        UserController.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public UserController(UserService userService, AlipayUtil alipayUtil) {
        this.userService = userService;
        this.alipayUtil = alipayUtil;
    }

    @PostMapping("/position/update")
    public Result updatePosition(@RequestBody Map<String, Integer> position, @PathVariable Integer userID){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pos = objectMapper.writeValueAsString(position);
            stringRedisTemplate.opsForValue().set(String.valueOf(userID), pos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.ok("更新成功");
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
        alipayRequest.setReturnUrl(AlipayController.RETURL);
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
        String pay = getPay();
        return pay == null ? Result.err(Result.CODE_ERR_BUSINESS, "获取二维码失败") : Result.ok("获取成功", pay);
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
        return Result.ok("取消成功", body);
    }

    @GetMapping("/orders/waiting")
    public Result waiting(@PathVariable Integer userID){
        Order order = new Order();
        order.setUserID(userID);
        order.setStatus(Order.ORDER_PAYED);
        List<Order> orders = userService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/history/finished")
    public Result finishedOrders(@PathVariable Integer userID){
        Order order = new Order();
        order.setUserID(userID);
        order.setStatus(Order.ORDER_FINISHED);
        List<Order> orders = userService.querySelfOrderByStatus(order);
        return Result.ok("获取成功", orders);
    }

    @GetMapping("/orders/history/cancelled")
    public Result cancelledOrders(@PathVariable Integer userID){
        Order order = new Order();
        order.setUserID(userID);
        order.setStatus(Order.ORDER_CANCELLED);
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

    private static String getPay(){
        try {
            File imgFile = new File("./src/main/resources/pay/default.png");
            FileInputStream fis = new FileInputStream(imgFile);
            byte[] data = new byte[(int) imgFile.length()];
            fis.read(data);
            fis.close();
            return Base64.getEncoder().encodeToString(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
