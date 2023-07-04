package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.CurrentAccount;
import com.ayf.areyoufull.entity.LoginAccount;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.exception.BusinessException;
import com.ayf.areyoufull.service.UserService;
import com.ayf.areyoufull.utils.Constraint;
import com.ayf.areyoufull.utils.DigestUtil;
import com.ayf.areyoufull.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

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
    public Result login(@RequestBody LoginAccount loginUser){
        String key = LoginController.stringRedisTemplate.opsForValue().get(loginUser.getVerificationKey());
        if (!loginUser.getVerificationCode().equals(key))
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码错误");
        User byID = userService.getUserByID(loginUser.getAccountID());
        if (DigestUtil.hmacSign(loginUser.getPassword()).equals(byID.getAccount().getPassword())) {
            String token = key;
            return Result.ok("登陆成功", token);
        } else
            return Result.err(Result.CODE_ERR_BUSINESS, "密码错误");
    }

    @GetMapping("/curr-user")
    public Result currUser(@RequestHeader(Constraint.HEADER_TOKEN_NAME) String clientToken) {
        CurrentAccount currentUser = TokenUtils.getCurrentUser(clientToken);
        return Result.ok(currentUser);
    }

    @GetMapping("/token/verify")
    public Result verifyToken(@RequestHeader(Constraint.HEADER_TOKEN_NAME) String clientToken){
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
    public Result logout(@RequestHeader(Constraint.HEADER_TOKEN_NAME) String clientToken) {
        TokenUtils.removeToken(clientToken);
        return Result.ok();
    }
}
