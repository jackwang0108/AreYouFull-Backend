package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.LoginUser;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logging")
public class LoginController {
    private UserService userService;
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        LoginController.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginUser loginUser){
        String key = LoginController.stringRedisTemplate.opsForValue().get(loginUser.getVerificationKey());
        if (!loginUser.getVerificationCode().equals(key))
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码错误");
        User byID = userService.getUserByID(loginUser.getAccountID());
        if (loginUser.getPassword().equals(byID.getAccount().getPassword())) {
            String token = key;
            return Result.ok("登陆成功", token);
        } else
            return Result.err(Result.CODE_ERR_BUSINESS, "密码错误");
    }
}
