package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.*;
import com.ayf.areyoufull.exception.BusinessException;
import com.ayf.areyoufull.service.DelivererService;
import com.ayf.areyoufull.service.ShopService;
import com.ayf.areyoufull.service.UserService;
import com.ayf.areyoufull.utils.Constant;
import com.ayf.areyoufull.utils.DigestUtil;
import com.ayf.areyoufull.utils.TokenUtils;
import com.mysql.cj.conf.ConnectionUrlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.zset.Tuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final UserService userService;
    private final DelivererService delivererService;
    private final ShopService shopService;
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        LoginController.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public LoginController(UserService userService, DelivererService delivererService, ShopService shopService) {
        this.userService = userService;
        this.delivererService = delivererService;
        this.shopService = shopService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginAccount loginAccount){
        Integer accountType = null;
        if (userService.getUserByUserID(loginAccount.getAccountID()) != null)
            accountType = 0;
        else if (delivererService.getDelivererByDelivererID(loginAccount.getAccountID()) != null)
            accountType = 1;
        else if (shopService.getShopByMerchantID(loginAccount.getAccountID()) != null)
            accountType = 2;
        else {
            accountType = -1;
        }
        String key = LoginController.stringRedisTemplate.opsForValue().get(loginAccount.getVerificationKey());
        if (!loginAccount.getVerificationCode().equals(key))
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码错误");
        switch (accountType){
            case 0 -> {
                User byID = userService.getUserByUserID(loginAccount.getAccountID());
                if (DigestUtil.hmacSign(loginAccount.getPassword()).equals(byID.getAccount().getPassword())) {
                    String token = key;
                    ConnectionUrlParser.Pair<Integer, String> integerStringPair = new ConnectionUrlParser.Pair<>(accountType, token);
                    return Result.ok("登陆成功", integerStringPair);
                } else
                    return Result.err(Result.CODE_ERR_BUSINESS, "密码错误");
            }
            case 1 -> {
                Deliverer byID = delivererService.getDelivererByDelivererID(loginAccount.getAccountID());
                if (DigestUtil.hmacSign(loginAccount.getPassword()).equals(byID.getAccount().getPassword())) {
                    String token = key;
                    ConnectionUrlParser.Pair<Integer, String> integerStringPair = new ConnectionUrlParser.Pair<>(accountType, token);
                    return Result.ok("登陆成功", integerStringPair);
                } else
                    return Result.err(Result.CODE_ERR_BUSINESS, "密码错误");
            }
            case 2 -> {
                Shop byID = shopService.getShopByMerchantID(loginAccount.getAccountID());
                if (DigestUtil.hmacSign(loginAccount.getPassword()).equals(byID.getAccount().getPassword())) {
                    String token = key;
                    ConnectionUrlParser.Pair<Integer, String> integerStringPair = new ConnectionUrlParser.Pair<>(accountType, token);
                    return Result.ok("登陆成功", integerStringPair);
                } else
                    return Result.err(Result.CODE_ERR_BUSINESS, "密码错误");
            }
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "账号错误");
    }

    @GetMapping("/curr-user")
    public Result currUser(@RequestHeader(Constant.HEADER_TOKEN_NAME) String clientToken) {
        CurrentAccount currentUser = TokenUtils.getCurrentAccount(clientToken);
        return Result.ok(currentUser);
    }

    @GetMapping("/token/verify")
    public Result verifyToken(@RequestHeader(Constant.HEADER_TOKEN_NAME) String clientToken){
        try {
            TokenUtils.verify(clientToken);
            return Result.ok();
        } catch (BusinessException e) {
            return Result.err(Result.CODE_ERR_UNLOGIN, e.getMessage());
        } catch (Exception e){
            return Result.err(Result.CODE_ERR_SYS, "系统错误");
        }
    }

    @DeleteMapping("/logout")
    public Result logout(@RequestHeader(Constant.HEADER_TOKEN_NAME) String clientToken) {
        TokenUtils.removeToken(clientToken);
        return Result.ok();
    }
}
