package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.service.DelivererService;
import com.ayf.areyoufull.service.ShopService;
import com.ayf.areyoufull.service.UserService;
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
@RequestMapping("/position")
public class PositionController {
    private final UserService userService;
    private final ShopService shopService;
    private final DelivererService delivererService;
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public PositionController(UserService userService, ShopService shopService, DelivererService delivererService) {
        this.userService = userService;
        this.shopService = shopService;
        this.delivererService = delivererService;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        PositionController.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/position/draw")
    public Result getPosition(@RequestBody Integer orderID){
        Order order = new Order();
        order.setOrderID(orderID);
        Order byID = userService.queryOrderByOrderID(order);
        Integer userID = byID.getUserID();
        Integer shopID = byID.getShopID();
        Shop shop = shopService.getShopByShopID(shopID);
        Integer merchantID = shop.getMerchantID();
        Integer delivererID = byID.getDelivererID();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map[] maps = new Map[3];
            maps[0] = objectMapper.readValue(stringRedisTemplate.opsForValue().get(String.valueOf(merchantID)), Map.class);
            maps[1] = objectMapper.readValue(stringRedisTemplate.opsForValue().get(String.valueOf(delivererID)), Map.class);
            maps[2] = objectMapper.readValue(stringRedisTemplate.opsForValue().get(String.valueOf(userID)), Map.class);
            return Result.ok("获取成功", maps);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "业务错误");
    }


    private static List<Map<String, Double>> userPos = new ArrayList<>();
    private static List<Map<String, Double>[]> delivererPos = new ArrayList<>();
    private static List<Map<String, Double>> shopPos = new ArrayList<>();
    public static void genePos(){
        HashMap<String, Double> pos = new HashMap<>();
        pos.put("lng", 108.953557);
        pos.put("lat", 34.267845);
        shopPos.add(pos);
        HashMap<String, Double> pos1 = new HashMap<>();
        pos1.put("lng", 108.985015);
        pos1.put("lat", 34.211311);
        shopPos.add(pos1);
        HashMap<String, Double> pos2 = new HashMap<>();
        pos2.put("lng", 108.929087);
        pos2.put("lat", 34.239547);
        shopPos.add(pos2);
        HashMap<String, Double> pos3 = new HashMap<>();
        pos3.put("lng", 108.910833);
        pos3.put("lat", 34.194949);
        shopPos.add(pos3);
        HashMap<String, Double> pos4 = new HashMap<>();
        pos4.put("lng", 108.954239);
        pos4.put("lat", 34.257511);
        shopPos.add(pos4);
        Map[] maps = new Map[11];
        maps[0].put("lng", 108.953588);
        maps[0].put("lat", 34.267356);
        maps[1].put("lng", 108.953552);
        maps[1].put("lat", 34.266879);
        maps[2].put("lng", 108.953516);
        maps[2].put("lat", 34.266193);
        maps[3].put("lng", 108.95348);
        maps[3].put("lat", 34.264791);
        maps[4].put("lng", 108.953489);
        maps[4].put("lat", 34.264545);
        maps[5].put("lng", 108.953471);
        maps[5].put("lat", 34.263262);
        maps[6].put("lng", 108.953489);
        maps[6].put("lat", 34.262725);
        maps[7].put("lng", 108.953417);
        maps[7].put("lat", 34.261278);
        maps[8].put("lng", 108.953382);
        maps[8].put("lat", 34.251715);
        maps[9].put("lng", 108.953382);
        maps[9].put("lat", 34.244732);
        maps[10].put("lng", 108.953382);
        maps[10].put("lat", 34.240554);
        delivererPos.add(maps);
        Map[] maps1 = new Map[11];
        maps1[0].put("lng", 108.985105);
        maps1[0].put("lat", 34.208937);
        maps1[1].put("lng", 108.985177);
        maps1[1].put("lat", 34.205026);
        maps1[2].put("lng", 108.985069);
        maps1[2].put("lat", 34.203698);
        maps1[3].put("lng", 108.985051);
        maps1[3].put("lat", 34.199324);
        maps1[4].put("lng", 108.989866);
        maps1[4].put("lat", 34.199757);
        maps1[5].put("lng", 108.994124);
        maps1[5].put("lat", 34.200399);
        maps1[6].put("lng", 108.99416);
        maps1[6].put("lat", 34.196039);
        maps1[7].put("lng", 108.997771);
        maps1[7].put("lat", 34.196756);
        maps1[8].put("lng", 109.001347);
        maps1[8].put("lat", 34.198622);
        maps1[9].put("lng", 109.001293);
        maps1[9].put("lat", 34.196622);
        maps1[10].put("lng", 109.001311);
        maps1[10].put("lat", 34.194263);
        delivererPos.add(maps1);
        Map[] maps2 = new Map[11];
        maps2[0].put("lng", 108.938645);
        maps2[0].put("lat", 34.235966);
        maps2[1].put("lng", 108.953305);
        maps2[1].put("lat", 34.236324);
        maps2[2].put("lng", 108.979823);
        maps2[2].put("lat", 34.239487);
        maps2[3].put("lng", 109.003682);
        maps2[3].put("lat", 34.249514);
        maps2[4].put("lng", 109.021648);
        maps2[4].put("lat", 34.248559);
        maps2[5].put("lng", 109.040692);
        maps2[5].put("lat", 34.257332);
        maps2[6].put("lng", 109.061461);
        maps2[6].put("lat", 34.257451);
        maps2[7].put("lng", 109.067498);
        maps2[7].put("lat", 34.267237);
        maps2[8].put("lng", 109.069941);
        maps2[8].put("lat", 34.275351);
        maps2[9].put("lng", 109.064408);
        maps2[9].put("lat", 34.282212);
        maps2[10].put("lng", 109.054418);
        maps2[10].put("lat", 34.280721);
        delivererPos.add(maps2);
        Map[] maps3 = new Map[11];
        maps3[0].put("lng", 108.909109);
        maps3[0].put("lat", 34.195427);
        maps3[1].put("lng", 108.923481);
        maps3[1].put("lat", 34.194949);
        maps3[2].put("lng", 108.933255);
        maps3[2].put("lat", 34.193994);
        maps3[3].put("lng", 108.933255);
        maps3[3].put("lat", 34.193994);
        maps3[4].put("lng", 108.954239);
        maps3[4].put("lat", 34.196144);
        maps3[5].put("lng", 108.953377);
        maps3[5].put("lat", 34.202354);
        maps3[6].put("lng", 108.95194);
        maps3[6].put("lat", 34.210475);
        maps3[7].put("lng", 108.952515);
        maps3[7].put("lat", 34.217162);
        maps3[8].put("lng", 108.953664);
        maps3[8].put("lat", 34.223371);
        maps3[9].put("lng", 108.954239);
        maps3[9].put("lat", 34.227669);
        maps3[10].put("lng", 108.954814);
        maps3[10].put("lat", 34.233638);
        delivererPos.add(maps3);
        Map[] maps4 = new Map[11];
        maps4[0].put("lng", 108.94734);
        maps4[0].put("lat", 34.256795);
        maps4[1].put("lng", 108.941879);
        maps4[1].put("lat", 34.257033);
        maps4[2].put("lng", 108.929805);
        maps4[2].put("lat", 34.256795);
        maps4[3].put("lng", 108.918882);
        maps4[3].put("lat", 34.257033);
        maps4[4].put("lng", 108.911696);
        maps4[4].put("lat", 34.256556);
        maps4[5].put("lng", 108.901347);
        maps4[5].put("lat", 34.257511);
        maps4[6].put("lng", 108.896748);
        maps4[6].put("lat", 34.26133);
        maps4[7].put("lng", 108.89646);
        maps4[7].put("lat", 34.265865);
        maps4[8].put("lng", 108.895885);
        maps4[8].put("lat", 34.272786);
        maps4[9].put("lng", 108.897898);
        maps4[9].put("lat", 34.278752);
        maps4[10].put("lng", 108.898185);
        maps4[10].put("lat", 34.287581);
        delivererPos.add(maps4);
        HashMap<String, Double> pos5 = new HashMap<>();
        pos.put("lng", 108.953557);
        pos.put("lat", 34.267845);
        userPos.add(pos5);
        HashMap<String, Double> pos6 = new HashMap<>();
        pos.put("lng", 109.002946);
        pos.put("lat", 34.189784);
        userPos.add(pos6);
        HashMap<String, Double> pos7 = new HashMap<>();
        pos.put("lng", 109.053844);
        pos.put("lat", 34.28078);
        userPos.add(pos7);
        HashMap<String, Double> pos8 = new HashMap<>();
        pos.put("lng", 108.953664);
        pos.put("lat", 34.236742);
        userPos.add(pos8);
        HashMap<String, Double> pos9 = new HashMap<>();
        pos.put("lng", 108.898473);
        pos.put("lat", 34.2945);
        userPos.add(pos9);
    }

    private static int cnt = 0;
    private static Map<String, Double>[] getCurrPos(Integer integer){
        Map[] maps = new Map[3];
        maps[0] = shopPos.get(integer % 5);
        maps[1] = delivererPos.get(integer % 5)[cnt];
        cnt = (cnt + 1) % 11;
        maps[2] = userPos.get(integer % 5);
        return maps;
    }
}
