package com.example.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    //设置过期时间
    private static final long EXPIRATION_TIME = 86400000;
    //设置密钥
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    /**
     * 生成jwt令牌
     * @param username
     * @param userId
     * @return
     */
    public static String generateToken(String username,Integer userId){
        return Jwts.builder()
                .setSubject(username)
                .claim("userId",userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * 解析jwt令牌
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
