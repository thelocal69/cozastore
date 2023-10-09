package com.cybersoft.cozastore.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTHelper {

    @Value("${custom.token.key}")
    private String cusKey;

    public  String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(cusKey));
        Date date = new Date();
        long expiredTime = 8 * 60 * 60 * 1000;
        long newDateMilis = date.getTime() + expiredTime;
        Date newExpiredDate = new Date(newDateMilis);

        return Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .setExpiration(newExpiredDate)
                .compact();
    }

    public String parserToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(cusKey));
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token).getBody()
                .getSubject();
    }
}
