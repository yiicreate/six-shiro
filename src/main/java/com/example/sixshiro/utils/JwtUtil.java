package com.example.sixshiro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.sixshiro.config.SixConf;
import com.example.sixshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: lh
 * @date: 2021/4/1
 */


public class JwtUtil {
    public static final String KEY = "SWAWP";
    public static final String TOKEN = "token";

    public static String create(String name,String password){
        Date date = new Date(System.currentTimeMillis()+ AppUtil.getBean(SixConf.class).getJwtExpireTime());
        Algorithm a =  Algorithm.HMAC256(KEY+password);
        String token = JWT.create()
                .withExpiresAt(date)  //设置过期时间
                .withClaim("name",name) //设置接受方信息，一般时登录用户
                .sign(a);  //使用HMAC算法，111111作为密钥加密
        return  token;
    }

    public static int verify(String token){
        String userName  = decodeToken(token);
        String password = AppUtil.getBean(UserService.class).getByName(userName).getPassword();
        try {
            Algorithm a =  Algorithm.HMAC256(KEY+password);
            JWTVerifier jwtVerifier = JWT.require(a).build();
            jwtVerifier.verify(token);
            return 0;
        }catch (TokenExpiredException e){
            return 1;
        }catch (Exception e){
            return 2;
        }
    }


    public static String decodeToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("name").asString();
    }
}
