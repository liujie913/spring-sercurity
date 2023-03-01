package com.lj.sercurity.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Slf4j
public class JWTUtil {
    //密钥
    public static String SECRET = "my-jwt";

    //创建token
    //传入userid
    public static String createToken(Long userId){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,24*60*60*7);
        JwtBuilder builder = Jwts.builder()
            .setHeaderParam("typ","JWT")
            .setSubject(userId+"")
            .setIssuedAt(new Date())
            .setExpiration(calendar.getTime())
            .signWith(SignatureAlgorithm.HS256,SECRET);
        return builder.compact();
    }

    //校验jwt
    public static Claims parseToken(String token){
        try{
            return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        }catch (Exception e){
            log.error("jwt match error:{}",e);
            return null;
        }
    }

    //判断token是否过期
    public boolean judgeTokenExpiration(Date expiration){
        return expiration.before(new Date());
    }

    public static void main(String[] args) {
        String token = JWTUtil.createToken(123l);
        System.out.println(token);
        Claims claims = JWTUtil.parseToken(token);
        System.out.println(11111);

    }
}
