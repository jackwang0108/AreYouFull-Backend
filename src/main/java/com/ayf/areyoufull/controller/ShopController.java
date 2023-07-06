package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.service.IDService;
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
        Integer id = IDService.getMerchandiseNextID();
        for (Merchandise merchandise : merchandises) {
            merchandise.setMerchandiseID(id++);
            merchandise.setShopID(shopID);
            merchandise.setMerchandiseStatus((byte) 0);
            merchandise.setMerchandiseImgPath(Merchandise.defaultImgPath);
        }
        shopService.publish(merchandises);
        return Result.ok("上架商品成功");
    }
}
