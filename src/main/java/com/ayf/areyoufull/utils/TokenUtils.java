package com.ayf.areyoufull.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ayf.areyoufull.dao.AccountDao;
import com.ayf.areyoufull.entity.CurrentAccount;
import com.ayf.areyoufull.exception.BusinessException;
import com.ayf.areyoufull.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class TokenUtils {
    private static AccountDao accountDao;
    private static final int expireTime = 60;
    private static StringRedisTemplate stringRedisTemplate;
    private static final String CLAIM_NAME_USERID = "CLAIM_NAME_USERID";
    private static final String CLAIM_NAME_USERNAME = "CLAIM_NAME_USERNAME";

    @Autowired
    public void setAccountDao(AccountDao accountDao){
        TokenUtils.accountDao = accountDao;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        TokenUtils.stringRedisTemplate = stringRedisTemplate;
    }

    private static String sign(CurrentAccount currentUser, String securityKey){
        return JWT.create()
                .withClaim(CLAIM_NAME_USERID, currentUser.getAccountID())
                .withClaim(CLAIM_NAME_USERNAME, currentUser.getNickname())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 1000L))
                .sign(Algorithm.HMAC256(securityKey));
    }

    public static String loginSign(CurrentAccount currentUser, String password){
        String token = sign(currentUser, password);
        stringRedisTemplate.opsForValue().set(token, token, expireTime * 2L, TimeUnit.SECONDS);
        return token;
    }

    public static CurrentAccount getCurrentAccount(String clientToken) {
        if(!StringUtils.hasText(clientToken)){
            throw new BusinessException("令牌为空！请登录。");
        }
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.decode(clientToken); // 获取一个解码后的令牌
        } catch (JWTDecodeException e) {
            throw new BusinessException("令牌格式错误！请登录。");
        }
        int userId = decodedJWT.getClaim(CLAIM_NAME_USERID).asInt(); // 获取用户账号id
        String userName = decodedJWT.getClaim(CLAIM_NAME_USERNAME).asString(); // 获取用户姓名
        if(!StringUtils.hasText(userName))
            throw new BusinessException("令牌缺失用户信息！请登录。");
        return new CurrentAccount(userId, userName);
    }

    public static void verify(String clientToken) {
        CurrentAccount currentUser = getCurrentAccount(clientToken);
        String password;
        try {
            password = accountDao.findByID(currentUser.getAccountID()).getPassword();
        } catch (Exception e) {
            log.error("系统错误！",e);
            throw new SysException("系统错误！",e);
        }

        if(!StringUtils.hasText(password)){
            throw new BusinessException("没有找到当前用户的密钥！请登录后再试。");
        }

        String cacheToken = stringRedisTemplate.opsForValue().get(clientToken); // 获得redis中存储的令牌
        if(!StringUtils.hasText(cacheToken)){ // redis中存储的令牌不存在
            throw new BusinessException("令牌过期！请重新登录。");
        }

        Algorithm algorithm = Algorithm.HMAC256(password);

        JWTVerifier jwtVerifier =  JWT.require(algorithm).build();

        try {
            jwtVerifier.verify(cacheToken);//验证redis中的令牌
        } catch (TokenExpiredException e) {//令牌本身过期异常
            String newToken = sign(currentUser, password);
            stringRedisTemplate.opsForValue().set(clientToken, newToken, expireTime * 2L, TimeUnit.SECONDS);
        } catch (Exception e){
            throw new BusinessException("非法令牌！请登录。");
        }
    }

    public static void removeToken(String clientToken){
        stringRedisTemplate.delete(clientToken);
    }
}

